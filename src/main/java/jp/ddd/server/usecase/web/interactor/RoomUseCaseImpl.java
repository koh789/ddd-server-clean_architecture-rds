package jp.ddd.server.usecase.web.interactor;

import jp.ddd.server.domain.entity.room.Room;
import jp.ddd.server.domain.entity.user.core.UserId;
import jp.ddd.server.domain.repository.RoomRepository;
import jp.ddd.server.domain.repository.UserRepository;
import jp.ddd.server.other.exception.AuthException;
import jp.ddd.server.usecase.web.inputport.RoomUseCase;
import lombok.val;
import org.eclipse.collections.api.list.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ユーザのUseCaseに関するInPutPortです。
 * Created by noguchi_kohei
 */
@Component
public class RoomUseCaseImpl implements RoomUseCase {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoomRepository roomRepository;

    @Override
    public Room register(String loginSessionId, String roomName, ImmutableList<UserId> joinUserIds) {
        val loginUserId = userRepository.getOptBySid(loginSessionId) //
          .map(u -> u.getId()).orElseThrow(() -> new AuthException());
        return roomRepository.register(loginUserId, roomName, joinUserIds);
    }
}
