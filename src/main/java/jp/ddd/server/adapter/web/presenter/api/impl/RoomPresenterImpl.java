package jp.ddd.server.adapter.web.presenter.api.impl;

import jp.ddd.server.adapter.web.presenter.api.output.ResultJson;
import jp.ddd.server.adapter.web.presenter.api.output.room.RegisteredRoomJson;
import jp.ddd.server.domain.entity.room.Room;
import jp.ddd.server.usecase.web.outputport.RoomPresenter;
import org.springframework.stereotype.Component;

/**
 * Created by noguchi_kohei 
 */
@Component
public class RoomPresenterImpl implements RoomPresenter {

    @Override
    public ResultJson<RegisteredRoomJson> toRegisteredJson(Room room) {
        return ResultJson.create(RegisteredRoomJson.builder().roomId(room.getRoomId().getId()).build());
    }
}
