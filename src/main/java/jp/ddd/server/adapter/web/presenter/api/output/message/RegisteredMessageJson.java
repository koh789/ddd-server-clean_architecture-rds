package jp.ddd.server.adapter.web.presenter.api.output.message;

import jp.ddd.server.adapter.web.presenter.api.output.Json;
import jp.ddd.server.adapter.gateway.rds.entity.MessageRds;
import jp.ddd.server.other.utils.Dates;
import lombok.Builder;
import lombok.Data;

/**
 * Created by noguchi_kohei 
 */
@Builder
@Data
public class RegisteredMessageJson implements Json {

    private Long messageId;
    /** yyyy/MM/dd hh:mm:ss */
    private String messageDt;
    /** yyyy/MM/dd hh:mm:ss */
    private String lastEditDt;

    public static RegisteredMessageJson create(MessageRds message) {
        return RegisteredMessageJson.builder().messageId(message.getId()).messageDt(Dates.toString(message.getMessageDt()))
          .lastEditDt(Dates.toString(message.getLastEditDt())).build();
    }
}
