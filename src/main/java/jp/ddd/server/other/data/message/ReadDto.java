package jp.ddd.server.other.data.message;

import jp.ddd.server.adapter.gateway.rds.entity.MessageReadRds;
import jp.ddd.server.adapter.gateway.rds.entity.UserRds;
import jp.ddd.server.other.data.Dto;
import jp.ddd.server.other.exception.NotFoundException;
import jp.ddd.server.other.utils.DsMaps;
import lombok.Builder;
import lombok.Value;
import lombok.val;
import org.eclipse.collections.api.map.ImmutableMap;

import java.util.Date;

/**
 * 既読情報を表現するdtoです。
 * @author noguchi_kohei
 *
 */
@Builder
@Value
public class ReadDto implements Dto {
    private static final long serialVersionUID = 1L;

    private final Long messageId;

    private final Integer readUserId;

    private final String readUserName;

    private final Date readDate;

    public static ReadDto create(MessageReadRds entity, ImmutableMap<Integer, UserRds> userMap) {
        val userName = DsMaps.getOpt(userMap, entity.getUserId()).map(u -> u.getName())
          .orElseThrow(() -> new NotFoundException());
        return ReadDto.builder()//
          .messageId(entity.getMessageId())//
          .readUserId(entity.getUserId())//
          .readUserName(userName)//
          .readDate(entity.getReadDt()).build();
    }
}
