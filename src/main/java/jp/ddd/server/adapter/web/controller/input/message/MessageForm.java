package jp.ddd.server.adapter.web.controller.input.message;

import jp.ddd.server.adapter.web.controller.input.Form;
import jp.ddd.server.other.utils.Msg;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by noguchi_kohei 
 */
@Data
public class MessageForm implements Form {
    private static final long serialVersionUID = 6040019966708493537L;
    @NotNull(message = Msg.NOT_NULL)
    private Integer userId;

    @NotNull(message = Msg.NOT_NULL)
    @Size(min = 1, max = 200, message = Msg.INPUT_TO200)
    private String content;
}
