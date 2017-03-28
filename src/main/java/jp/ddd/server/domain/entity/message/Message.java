package jp.ddd.server.domain.entity.message;

import jp.ddd.server.domain.entity.Entity;
import jp.ddd.server.domain.entity.core.LastEditDt;
import jp.ddd.server.domain.entity.core.MessageDt;
import jp.ddd.server.domain.entity.core.MessageId;
import jp.ddd.server.domain.entity.room.core.RoomId;
import jp.ddd.server.domain.entity.user.core.UserId;
import jp.ddd.server.adapter.gateway.rds.entity.MessageRds;
import jp.ddd.server.other.utils.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import org.eclipse.collections.api.list.ImmutableList;

/**
 * The persistent class for the message database table.
 */
@Builder
@AllArgsConstructor
@Value
public class Message implements Entity<Message> {
    private static final long serialVersionUID = 5227353660764112526L;

    private final MessageId messageId;

    private final RoomId roomId;

    private final String content;

    private final LastEditDt lastEditDt;

    private final MessageDt messageDt;

    private final Status status;

    private final UserId userId;

    private final ImmutableList<MessageRead> messageReads;

    public static Message create(MessageRds messageRds) {
        return Message.builder()//
          .messageId(new MessageId(messageRds.getId()))//
          .roomId(new RoomId(messageRds.getRoomId()))//
          .content(messageRds.getContent())//
          .lastEditDt(new LastEditDt(messageRds.getLastEditDt()))//
          .messageDt(new MessageDt(messageRds.getMessageDt()))//
          .status(messageRds.getStatus())//
          .userId(new UserId(messageRds.getUserId())).build();
    }
}