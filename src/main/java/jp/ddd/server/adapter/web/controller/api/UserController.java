package jp.ddd.server.adapter.web.controller.api;

import jp.ddd.server.adapter.web.controller.BaseApi;
import jp.ddd.server.adapter.web.controller.input.user.UserForm;
import jp.ddd.server.adapter.web.presenter.api.output.ResultJson;
import jp.ddd.server.adapter.web.presenter.api.output.user.RegisteredUserJson;
import jp.ddd.server.domain.entity.user.User;
import jp.ddd.server.other.annotation.NotLoginRequired;
import jp.ddd.server.other.exception.IllegalDataException;
import jp.ddd.server.other.utils.Msg;
import jp.ddd.server.usecase.web.inputport.UserUseCase;
import jp.ddd.server.usecase.web.outputport.UserPresenter;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by noguchi_kohei 
 */
@RestController
@RequestMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController extends BaseApi {
    @Autowired
    private UserUseCase userUseCase;
    @Autowired
    private UserPresenter userPresenter;

    @NotLoginRequired
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResultJson<RegisteredUserJson> register(@RequestBody @Validated UserForm userForm, HttpServletRequest req) {
        if (!userForm.getPassword().equals(userForm.getConfirmedPassword())) {
            new IllegalDataException(Msg.MISMATCH_PASS, true);
        }
        val user = User.create(userForm);
        if (userUseCase.isExist(user.getLoginId())) {
            new IllegalDataException(Msg.EXISTED_LOGIN_ID, true);
        }
        return userPresenter.toRegisteredJson(userUseCase.register(user));
    }
}
