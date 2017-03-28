package jp.ddd.server.other.data.message;

import jp.ddd.server.other.data.Dto;
import jp.ddd.server.other.utils.Dates;
import jp.ddd.server.adapter.web.controller.input.message.MessageForm;
import lombok.Builder;
import lombok.Value;

import java.util.Date;

/**
 * Created by noguchi_kohei 
 */
@Builder
@Value
public class RegisterMessageParam implements Dto {
    private static final long serialVersionUID = -2826772620643136511L;

    private final Integer roomId;

    private final Integer userId;

    private final String content;

    private final Date messageDt;

    public static RegisterMessageParam create(Integer roomId, MessageForm form) {
        return RegisterMessageParam.builder().roomId(roomId).userId(form.getUserId()).content(form.getContent())
          .messageDt(Dates.now()).build();
    }
}
