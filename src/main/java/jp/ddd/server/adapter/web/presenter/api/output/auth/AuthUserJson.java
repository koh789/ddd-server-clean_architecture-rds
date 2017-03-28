package jp.ddd.server.adapter.web.presenter.api.output.auth;

import jp.ddd.server.domain.entity.user.User;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by noguchi_kohei 
 */
@Builder
@Data
public class AuthUserJson implements Serializable {
    private static final long serialVersionUID = 910139523300534581L;

    /**
     * 利用者を特定するID
     */
    private final String sessionId;

    private final Integer userId;

    private final String loginId;

    private final String name;

    private final String email;

    private final String tel;

    public static AuthUserJson create(String sid, User user) {

        return AuthUserJson.builder().sessionId(sid).userId(user.getId().getId()).loginId(user.getLoginId().getId())
          .name(user.getUserInfo().getName()).email(user.getUserInfo().getEmail()).tel(user.getUserInfo().getTel())
          .build();
    }
}
