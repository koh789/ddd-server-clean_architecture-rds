package jp.ddd.server.usecase.repository.impl;

import jp.ddd.server.adapter.gateway.rds.entity.UserRds;
import jp.ddd.server.adapter.gateway.redis.entity.SessionUser;
import jp.ddd.server.domain.entity.user.core.HashPass;
import jp.ddd.server.domain.entity.user.core.LoginId;
import jp.ddd.server.domain.entity.user.core.UserId;
import jp.ddd.server.domain.repository.UserRepository;
import jp.ddd.server.other.exception.AuthException;
import jp.ddd.server.other.exception.IllegalDataException;
import jp.ddd.server.usecase.gateway.rds.UserRdsGateway;
import jp.ddd.server.usecase.gateway.redis.SessionUserRedisGateway;
import lombok.val;
import org.eclipse.collections.api.list.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 *
 * {@link UserRepository}実装クラスです。
 * 当クラスでDDDにおけるドメインレポジトリを表現します。
 * Created by noguchi_kohei
 */
@Component
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    private UserRdsGateway userRdsGateway;
    @Autowired
    private SessionUserRedisGateway sessionUserRedisGateway;

    @Override
    public jp.ddd.server.domain.entity.user.User register(jp.ddd.server.domain.entity.user.User user) {
        if (isExist(user.getLoginId())) {
            throw new IllegalDataException("登録済みloginIdです。" + user.getLoginId().getId());
        }

        val result = userRdsGateway.save(UserRds.create(user));
        return jp.ddd.server.domain.entity.user.User.create(result);
    }

    @Override
    public boolean isExist(LoginId loginId) {
        return userRdsGateway.getOpt(loginId.getId()).isPresent();
    }

    @Override
    public Optional<jp.ddd.server.domain.entity.user.User> getOpt(LoginId loginId, HashPass hashPass) {
        return userRdsGateway.getOpt(loginId.getId(), hashPass.getPass())
          .map(res -> jp.ddd.server.domain.entity.user.User.create(res));
    }

    @Override
    public ImmutableList<jp.ddd.server.domain.entity.user.User> find(ImmutableList<UserId> userIds) {
        return userRdsGateway.find(userIds.collect(uid -> uid.getId()))
          .collect(res -> jp.ddd.server.domain.entity.user.User.create(res));
    }

    @Override
    public jp.ddd.server.domain.entity.user.User login(String sid, LoginId loginId, HashPass hashPass) {
        return userRdsGateway.getOpt(loginId.getId(), hashPass.getPass()) //
          .map(u -> {
              val sessionUser = SessionUser.create(sid, jp.ddd.server.domain.entity.user.User.create(u));
              return sessionUserRedisGateway.save(sessionUser).getUser();
          }).orElseThrow(() -> new AuthException("invalid loginId and password!" + loginId.getId()));
    }

    @Override
    public void logout(String sid) {
        sessionUserRedisGateway.del(sid);
    }

    @Override
    public boolean isLogin(String sid) {
        return sessionUserRedisGateway.getOpt(sid).isPresent();
    }

    @Override
    public Optional<jp.ddd.server.domain.entity.user.User> getOptBySid(String sid) {
        return sessionUserRedisGateway.getOpt(sid).map(su -> su.getUser());
    }
}
