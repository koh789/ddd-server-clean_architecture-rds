package jp.ddd.server.usecase.repository.impl;

import jp.ddd.server.usecase.gateway.rds.MessageReadRdsGateway;
import jp.ddd.server.usecase.gateway.rds.MessageRdsGateway;
import jp.ddd.server.domain.entity.message.Message;
import jp.ddd.server.domain.entity.room.core.RoomId;
import jp.ddd.server.domain.entity.user.core.UserId;
import jp.ddd.server.domain.repository.MessageRepository;
import jp.ddd.server.adapter.gateway.rds.entity.MessageRds;
import jp.ddd.server.adapter.gateway.rds.entity.MessageReadRds;
import jp.ddd.server.other.data.common.Page;
import jp.ddd.server.other.utils.Dates;
import lombok.val;
import org.eclipse.collections.api.list.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * {@link MessageRepository}実装クラスです。
 * 当クラスでDDDにおけるドメインレポジトリを表現します。
 * Created by noguchi_kohei
 */
@Component
public class MessageRepositoryImpl implements MessageRepository {
    @Autowired
    private MessageRdsGateway messageRdsGateway;
    @Autowired
    private MessageReadRdsGateway messageReadRdsGateway;

    @Override
    public Message register(Message message) {
        val result = messageRdsGateway.save(MessageRds.create(message));
        return Message.create(result);
    }

    @Override
    public ImmutableList<Message> findAndSaveRead(RoomId roomId, UserId userId, Page page) {
        messageRdsGateway.findUnread(roomId.getId(), userId.getId())
          .each(m -> messageReadRdsGateway.save(MessageReadRds.create(m.getId(), userId.getId(), Dates.now())));
        return messageRdsGateway.findByRoomId(roomId.getId(), page).collect(m -> Message.create(m));
    }
}
