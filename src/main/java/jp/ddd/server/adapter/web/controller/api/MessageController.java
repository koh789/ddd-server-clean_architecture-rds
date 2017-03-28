package jp.ddd.server.adapter.web.controller.api;

import jp.ddd.server.usecase.gateway.redis.SessionUserRedisGateway;
import jp.ddd.server.adapter.web.controller.BaseApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by noguchi_kohei 
 */
@RestController
@RequestMapping(value = "/api/v1/rooms/{roomId}/messages", produces = MediaType.APPLICATION_JSON_VALUE)
public class MessageController extends BaseApi {
    @Autowired
    private SessionUserRedisGateway sessionUserRedisGateway;

    //    @RequestMapping(value = "", method = RequestMethod.POST)
    //    public ResultJson<RegisteredMessageJson> register(HttpServletRequest req, @PathVariable("roomId") Integer roomId,
    //      @RequestBody @Validated MessageForm form) {
    //        val user = SessionUser.getOpt(sessionUserRedisGateway, Cookies.getKey(req))
    //          .orElseThrow(() -> new AuthException());
    //        if (!user.getUserId().equals(form.getUserId())) {
    //            throw new AccessPermissonException(Msg.FORBIDDEN_MSG_POST, true);
    //        }
    //        val message = messageService.register(RegisterMessageParam.create(roomId, form));
    //        return ResultJson.create(RegisteredMessageJson.create(message));
    //    }
    //
    //    @RequestMapping(value = "", method = RequestMethod.GET)
    //    public ResultJson<ImmutableList<MessagesJson>> get(HttpServletRequest req, @PathVariable("roomId") Integer roomId,
    //      @RequestParam(value = "p", defaultValue = "1") int pageNum) {
    //        val user = SessionUser.getOpt(sessionUserRedisGateway, Cookies.getKey(req))
    //          .orElseThrow(() -> new AuthException());
    //
    //        val dtos = messageService.find(roomId, user.getUserId(), Page.create(pageNum, 50));
    //
    //        return ResultJson.create(dtos.collect(dto -> MessagesJson.create(dto)));
    //    }
}
