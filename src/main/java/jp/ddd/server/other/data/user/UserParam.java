package jp.ddd.server.other.data.user;

import jp.ddd.server.adapter.web.controller.input.user.UserForm;
import jp.ddd.server.other.data.Dto;
import lombok.Builder;
import lombok.Value;

/**
 * Created by noguchi_kohei 
 */
@Builder
@Value
public class UserParam implements Dto {
    private static final long serialVersionUID = 3975780308336578986L;

    private final String loginId;
    private final String name;
    private final String rawPass;
    private String email;
    private String tel;

    public static UserParam create(UserForm form) {
        return UserParam.builder() //
          .loginId(form.getLoginId()) //
          .name(form.getName()) //
          .rawPass(form.getPassword()) //
          .email(form.getEmail()) //
          .tel(form.getTel()).build();
    }
}
