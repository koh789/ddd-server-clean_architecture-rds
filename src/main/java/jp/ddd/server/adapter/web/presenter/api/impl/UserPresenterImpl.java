package jp.ddd.server.adapter.web.presenter.api.impl;

import jp.ddd.server.adapter.web.presenter.api.output.ResultJson;
import jp.ddd.server.adapter.web.presenter.api.output.auth.AuthUserJson;
import jp.ddd.server.adapter.web.presenter.api.output.user.RegisteredUserJson;
import jp.ddd.server.domain.entity.user.User;
import jp.ddd.server.usecase.web.outputport.UserPresenter;
import lombok.val;
import org.springframework.stereotype.Component;

/**
 * Created by noguchi_kohei 
 */
@Component
public class UserPresenterImpl implements UserPresenter {

    @Override
    public ResultJson<RegisteredUserJson> toRegisteredJson(User user) {
        val json = RegisteredUserJson.builder().userId(user.getId().getId()).build();
        return ResultJson.create(json);
    }

    @Override
    public ResultJson<AuthUserJson> toAuthenticatedJson(String sid, User user) {
        val json = AuthUserJson.builder().sessionId(sid).userId(user.getId().getId()).loginId(user.getLoginId().getId())
          .name(user.getUserInfo().getName()).email(user.getUserInfo().getEmail()).tel(user.getUserInfo().getTel())
          .build();
        return ResultJson.create(json);
    }

    @Override
    public ResultJson<String> toOk() {
        return ResultJson.create("OK");
    }
}
