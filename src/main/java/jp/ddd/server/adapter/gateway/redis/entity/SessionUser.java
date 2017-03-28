package jp.ddd.server.adapter.gateway.redis.entity;

import jp.ddd.server.domain.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * Created by noguchi_kohei 
 */
@Builder
@AllArgsConstructor
@Value
public class SessionUser implements Serializable {
    private static final long serialVersionUID = -324299695194671919L;

    private final String sessionId;

    private final User user;

    public static SessionUser create(String sessionId, User user) {
        return SessionUser.builder().sessionId(sessionId).user(user).build();
    }
}
