package jp.ddd.server.usecase.repository.impl;

import jp.ddd.server.adapter.gateway.rds.entity.RoomRds;
import jp.ddd.server.usecase.gateway.rds.RoomRdsGateway;
import jp.ddd.server.usecase.gateway.rds.RoomUserRdsGateway;
import jp.ddd.server.domain.entity.room.Room;
import jp.ddd.server.domain.entity.room.RoomUser;
import jp.ddd.server.domain.entity.room.core.LastMessageDt;
import jp.ddd.server.domain.entity.room.core.RoomId;
import jp.ddd.server.domain.entity.user.core.UserId;
import jp.ddd.server.adapter.gateway.rds.entity.RoomUserRds;
import jp.ddd.server.other.exception.NotFoundException;
import jp.ddd.server.other.utils.Dates;
import jp.ddd.server.domain.repository.RoomRepository;
import lombok.val;
import org.eclipse.collections.api.list.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 *
 * {@link RoomRepository}実装クラスです。
 * 当クラスでDDDにおけるドメインレポジトリを表現します。
 * Created by noguchi_kohei
 */
@Component
public class RoomRepositoryImpl implements RoomRepository {
    @Autowired
    private RoomRdsGateway roomRdsGateway;
    @Autowired
    private RoomUserRdsGateway roomUserRdsGateway;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Room register(UserId userId, String roomName, ImmutableList<UserId> joinUserIds) {
        val now = Dates.now();
        val extRoom = roomRdsGateway.save(RoomRds.create(userId.getId(), roomName, now));

        val extRoomUsers = joinUserIds //
          .distinct().collect(uid -> RoomUserRds.create(extRoom.getId(), uid.getId(), now))
          .collect(entity -> roomUserRdsGateway.save(entity));

        return Room.create(extRoom, extRoomUsers);
    }

    @Override
    public Optional<Room> getOpt(RoomId roomId) {
        return roomRdsGateway.getOpt(roomId.getId()).map(r -> Room.create(r, roomUserRdsGateway.findByRoomId(r.getId())));
    }

    @Override
    public void updateLastMsgDt(RoomId roomId, LastMessageDt lastMessageDt) {
        RoomRds roomRds = roomRdsGateway.getOpt(roomId.getId()) //
          .orElseThrow(() -> new NotFoundException("対象roomが存在しません" + roomId.getId()));
        roomRds.setLastMessageDt(lastMessageDt.getDate());
        roomRdsGateway.save(roomRds);
    }

    @Override
    public ImmutableList<RoomUser> findRoomUser(RoomId roomId) {
        return roomUserRdsGateway.findByRoomId(roomId.getId()).collect(eru -> RoomUser.create(eru));
    }
}
