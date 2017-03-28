package jp.ddd.server.other.data.auth;

import jp.ddd.server.other.data.Dto;
import jp.ddd.server.other.utils.Cookies;
import jp.ddd.server.other.utils.Requests;
import jp.ddd.server.adapter.web.controller.input.auth.AuthForm;
import lombok.Builder;
import lombok.Value;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by noguchi_kohei 
 */
@Builder
@Value
public class AuthParam implements Dto {
    private static final long serialVersionUID = 284671227623318535L;

    /**
     * 利用者を特定するID
     */
    private final String sessionId;
    /**
     * 利用者のIPアドレス
     */
    private final String ipAddress;

    private final String loginId;

    private final String password;

    public static AuthParam create(AuthForm form, HttpServletRequest req) {
        return AuthParam.builder()//
          .sessionId(Cookies.getKey(req)).ipAddress(Requests.getRemoteAddress(req)).loginId(form.getLoginId())
          .password(form.getPassword()).build();
    }
}
