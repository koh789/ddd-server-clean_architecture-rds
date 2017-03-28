package jp.ddd.server.domain.entity.message;

import jp.ddd.server.domain.entity.Entity;
import jp.ddd.server.domain.entity.core.MessageId;
import jp.ddd.server.domain.entity.core.MessageReadId;
import jp.ddd.server.domain.entity.core.ReadDt;
import jp.ddd.server.domain.entity.user.core.UserId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Builder
@AllArgsConstructor
@Value
public class MessageRead implements Entity<MessageRead> {
    private static final long serialVersionUID = 2377932200497420692L;

    private final MessageReadId messageReadId;

    private final ReadDt readDt;

    private final MessageId messageId;

    private final UserId userId;

}