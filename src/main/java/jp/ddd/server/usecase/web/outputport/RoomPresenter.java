package jp.ddd.server.usecase.web.outputport;

import jp.ddd.server.adapter.web.presenter.output.ResultJson;
import jp.ddd.server.adapter.web.presenter.output.room.RegisteredRoomJson;
import jp.ddd.server.domain.entity.room.Room;

/**
 * ユーザのUseCaseに関するInPutPortです。
 * Created by noguchi_kohei
 */
public interface RoomPresenter {

    ResultJson<RegisteredRoomJson> toRegisteredJson(Room room);
}
