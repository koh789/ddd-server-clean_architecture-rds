package jp.ddd.server.domain.repository;

import jp.ddd.server.domain.entity.user.core.HashPass;
import jp.ddd.server.domain.entity.user.core.LoginId;
import jp.ddd.server.domain.entity.user.User;
import jp.ddd.server.domain.entity.user.core.UserId;
import org.eclipse.collections.api.list.ImmutableList;

import java.util.Optional;

/**
 * ユーザに関するレポジトリを表現します。
 * ユーザは集約ルートエンティティのため、集約ルート内のオブジェクトに関しては
 * 全てこのレポジトリを使用して取得することを想定しています。
 * Created by noguchi_kohei
 */
public interface UserRepository {

    User register(User user);

    boolean isExist(LoginId loginId);

    Optional<User> getOpt(LoginId loginId, HashPass pass);

    ImmutableList<User> find(ImmutableList<UserId> userIds);

    User login(String sid, LoginId loginId, HashPass hashPass);

    void logout(String sid);

    boolean isLogin(String sid);

    Optional<User> getOptBySid(String sid);
}
