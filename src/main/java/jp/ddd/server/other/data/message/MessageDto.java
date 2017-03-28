package jp.ddd.server.other.data.message;

import jp.ddd.server.adapter.gateway.rds.entity.MessageRds;
import jp.ddd.server.adapter.gateway.rds.entity.UserRds;
import jp.ddd.server.other.data.Dto;
import jp.ddd.server.other.exception.NotFoundException;
import jp.ddd.server.other.utils.DsLists;
import jp.ddd.server.other.utils.DsMaps;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.val;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.map.ImmutableMap;

import java.util.Date;

/**
 * メッセージを表現するdtoです。
 *
 * @author noguchi_kohei
 */
@Builder
@AllArgsConstructor
@Value
public class MessageDto implements Dto {
    private static final long serialVersionUID = 1L;

    private final Integer roomId;

    private final Long messageId;

    private final Integer messageUserId;

    private final String messageUserName;

    private final String content;

    private final Date messageDate;

    private final ImmutableList<ReadDto> reads;

    public static MessageDto create(MessageRds entity, ImmutableMap<Integer, UserRds> userMap) {
        val reads = DsLists.toImt(entity.getMessageReads()) //
          .collect(mr -> ReadDto.create(mr, userMap));

        val messageUserName = DsMaps.getOpt(userMap, entity.getUserId()).map(u -> u.getName())
          .orElseThrow(() -> new NotFoundException());
        return MessageDto.builder()//
          .roomId(entity.getRoomId())//
          .messageId(entity.getId())//
          .messageUserId(entity.getUserId()) //
          .messageUserName(messageUserName)//
          .content(entity.getContent())//
          .messageDate(entity.getMessageDt())//
          .reads(reads)//
          .build();
    }
}
