package jp.ddd.server.adapter.web.controller.api;

import jp.ddd.server.adapter.web.controller.BaseApi;
import jp.ddd.server.adapter.web.controller.input.room.RoomForm;
import jp.ddd.server.adapter.web.presenter.api.output.ResultJson;
import jp.ddd.server.adapter.web.presenter.api.output.room.RegisteredRoomJson;
import jp.ddd.server.domain.entity.user.core.UserId;
import jp.ddd.server.domain.repository.RoomRepository;
import jp.ddd.server.domain.repository.UserRepository;
import jp.ddd.server.other.utils.Cookies;
import jp.ddd.server.other.utils.DsLists;
import jp.ddd.server.usecase.web.inputport.RoomUseCase;
import jp.ddd.server.usecase.web.outputport.RoomPresenter;
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
@RequestMapping(value = "/api/v1/rooms", produces = MediaType.APPLICATION_JSON_VALUE)
public class RoomController extends BaseApi {
    @Autowired
    private RoomUseCase roomUseCase;
    @Autowired
    private RoomPresenter roomPresenter;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResultJson<RegisteredRoomJson> register(HttpServletRequest req, @RequestBody @Validated RoomForm roomForm) {

        val joinUserIds = DsLists.toImt(roomForm.getJoinUserIds()).collect(uid -> new UserId(uid));
        return roomPresenter.toRegisteredJson( //
          roomUseCase.register(Cookies.getKey(req), roomForm.getRoomName(), joinUserIds));
    }

    //    @RequestMapping(value = "/{roomId}", method = RequestMethod.PUT)
    //    public ResultJson<String> add(HttpServletRequest req, @PathVariable("roomId") Integer roomId,
    //      @RequestBody @Validated UpdateRoomForm form) {
    //
    //        val loginUserId = SessionUser.getOpt(sessionUserRepository, Cookies.getKey(req)) //
    //          .map(su -> su.getUserId()).orElseThrow(() -> new AuthException());
    //
    //        val results = RoomRds //
    //          .addRoomUser(roomId, DsLists.toImt(form.getUserIds()), roomUserRepository)
    //          .collect(ru -> RegisteredRoomUserJson.create(ru.getId(), ru.getUserId(), ru.getJoinDt()));
    //
    //        return ResultJson.create(results);
    //    }
}
