package jp.ddd.server.adapter.web.presenter.output.message;

import jp.ddd.server.adapter.web.presenter.output.Json;
import jp.ddd.server.other.data.message.MessageDto;
import jp.ddd.server.other.utils.Dates;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Created by noguchi_kohei 
 */
@Builder
@Data
public class MessagesJson implements Json {

    private Integer roomId;

    private Long messageId;

    private Integer messageUserId;

    private String messageUserName;

    private String content;

    private String messageDate;

    private List<ReadJson> reads;

    public static MessagesJson create(MessageDto dto) {
        return MessagesJson.builder()//
          .roomId(dto.getRoomId()) //
          .messageId(dto.getMessageId())//
          .messageUserId(dto.getMessageUserId())//
          .messageUserName(dto.getMessageUserName())//
          .content(dto.getContent())//
          .messageDate(Dates.toString(dto.getMessageDate()))//
          .reads(dto.getReads().collect(r -> ReadJson.create(r)).castToList()) //
          .build();
    }
}
