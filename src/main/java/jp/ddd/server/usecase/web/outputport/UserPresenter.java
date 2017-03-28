package jp.ddd.server.usecase.web.outputport;

import jp.ddd.server.adapter.web.presenter.api.output.ResultJson;
import jp.ddd.server.adapter.web.presenter.api.output.auth.AuthUserJson;
import jp.ddd.server.adapter.web.presenter.api.output.user.RegisteredUserJson;
import jp.ddd.server.domain.entity.user.User;

/**
 * ユーザのUseCaseに関するOutPutPortです。
 * Created by noguchi_kohei 
 */
public interface UserPresenter {

    ResultJson<RegisteredUserJson> toRegisteredJson(User user);

    ResultJson<AuthUserJson> toAuthenticatedJson(String sid, User user);

    ResultJson<String> toOk();
}
