package jp.ddd.server.usecase.gateway.redis;

import jp.ddd.server.adapter.gateway.redis.entity.SessionUser;

import java.util.Optional;

/**
 * Created by noguchi_kohei 
 */
public interface SessionUserRedisGateway {

    SessionUser save(SessionUser sessionUser);

    Optional<SessionUser> getOpt(String sid);

    void del(String sid);
}
