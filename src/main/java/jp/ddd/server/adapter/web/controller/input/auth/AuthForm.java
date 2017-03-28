package jp.ddd.server.adapter.web.controller.input.auth;

import jp.ddd.server.adapter.web.controller.input.Form;
import jp.ddd.server.other.utils.Msg;
import jp.ddd.server.other.utils.Regex;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by noguchi_kohei 
 */
@Data
public class AuthForm implements Form {
    private static final long serialVersionUID = 7237378179106647211L;

    @NotNull(message = Msg.NOT_NULL)
    @Pattern(regexp = Regex.EMAIL_AND_TEL, message = Msg.ONLY_TEL_OR_EMAIL)
    private String loginId;
    @NotNull(message = Msg.NOT_NULL)
    @Pattern(regexp = Regex.PASSWORD, message = Msg.ONLY_EN_OR_NUM)
    private String password;
}
