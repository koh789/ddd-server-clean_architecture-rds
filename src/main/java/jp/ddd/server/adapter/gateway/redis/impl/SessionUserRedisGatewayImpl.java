package jp.ddd.server.adapter.gateway.redis.impl;

import jp.ddd.server.usecase.gateway.redis.SessionUserRedisGateway;
import jp.ddd.server.adapter.gateway.redis.base.RedisMapper;
import jp.ddd.server.adapter.gateway.redis.entity.SessionUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by noguchi_kohei 
 */
@Repository
public class SessionUserRedisGatewayImpl implements SessionUserRedisGateway {
    @Autowired
    private RedisMapper<SessionUser> redisMapper;
    private final String nameSpace = "nameSpaceSessionUser";

    @Override
    public SessionUser save(SessionUser sessionUser) {

        return redisMapper.save(SessionUser.class, nameSpace, sessionUser.getSessionId(), sessionUser);
    }

    @Override
    public Optional<SessionUser> getOpt(String sid) {
        return redisMapper.getOpt(SessionUser.class, nameSpace, sid);
    }

    @Override
    public void del(String sid) {
        redisMapper.delete(SessionUser.class, nameSpace, sid);
    }
}
