package jp.ddd.server.usecase.web.inputport;

import jp.ddd.server.domain.entity.user.User;
import jp.ddd.server.domain.entity.user.core.HashPass;
import jp.ddd.server.domain.entity.user.core.LoginId;

import java.util.Optional;

/**
 * ユーザのUseCaseに関するInPutPortです。
 * Created by noguchi_kohei
 */
public interface UserUseCase {

    User register(User user);

    boolean isExist(LoginId loginId);

    User login(String sid, LoginId loginId, HashPass hashPass);

    void logout(String sid);

    Optional<User> getOptBySid(String sid);
}
