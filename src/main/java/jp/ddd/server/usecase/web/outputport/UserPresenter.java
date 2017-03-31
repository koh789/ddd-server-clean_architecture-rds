package jp.ddd.server.usecase.web.outputport;

import jp.ddd.server.adapter.web.presenter.output.ResultJson;
import jp.ddd.server.adapter.web.presenter.output.auth.AuthUserJson;
import jp.ddd.server.adapter.web.presenter.output.user.RegisteredUserJson;
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
