package jp.ddd.server.usecase.web.interactor;

import jp.ddd.server.domain.entity.user.User;
import jp.ddd.server.domain.entity.user.core.HashPass;
import jp.ddd.server.domain.entity.user.core.LoginId;
import jp.ddd.server.domain.repository.UserRepository;
import jp.ddd.server.usecase.web.inputport.UserUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by noguchi_kohei 
 */
@Component
public class UserUseCaseImpl implements UserUseCase {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User register(User user) {
        return userRepository.register(user);
    }

    @Override
    public boolean isExist(LoginId loginId) {
        return userRepository.isExist(loginId);
    }

    @Override
    public User login(String sid, LoginId loginId, HashPass hashPass) {
        return userRepository.login(sid, loginId, hashPass);
    }

    @Override
    public void logout(String sid) {
        userRepository.logout(sid);
    }

    @Override
    public Optional<User> getOptBySid(String sid) {
        return userRepository.getOptBySid(sid);
    }
}
