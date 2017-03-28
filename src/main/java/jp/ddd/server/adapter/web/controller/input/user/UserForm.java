package jp.ddd.server.adapter.web.controller.input.user;

import jp.ddd.server.adapter.web.controller.input.Form;
import jp.ddd.server.other.utils.Msg;
import jp.ddd.server.other.utils.Regex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by noguchi_kohei 
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserForm implements Form {
    private static final long serialVersionUID = -2335451569337578177L;

    @NotNull(message = Msg.NOT_NULL)
    @Pattern(regexp = Regex.EMAIL_AND_TEL, message = Msg.ONLY_TEL_OR_EMAIL)
    private String loginId;

    @Size(min = 3, max = 30, message = Msg.INPUT_3TO30)
    @NotNull(message = Msg.NOT_NULL)
    private String name;

    @NotNull(message = Msg.NOT_NULL)
    @Size(min = 6, max = 30, message = Msg.INPUT_6TO30)
    @Pattern(regexp = Regex.PASSWORD, message = Msg.ONLY_EN_OR_NUM)
    private String password;

    @NotNull(message = Msg.NOT_NULL)
    @Size(min = 6, max = 30, message = Msg.INPUT_6TO30)
    @Pattern(regexp = Regex.PASSWORD, message = Msg.ONLY_EN_OR_NUM)
    private String confirmedPassword;

    @NotNull(message = Msg.NOT_NULL)
    @Pattern(regexp = Regex.EMAIL_AND_TEL, message = Msg.ONLY_TEL_OR_EMAIL)
    private String email;

    @Pattern(regexp = Regex.TEL, message = Msg.ONLY_TEL)
    private String tel;
}
