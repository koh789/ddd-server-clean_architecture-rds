package jp.ddd.server.adapter.web.presenter.api.output.room;

import jp.ddd.server.adapter.web.presenter.api.output.Json;
import jp.ddd.server.domain.entity.room.Room;
import lombok.Builder;
import lombok.Data;

/**
 * Created by noguchi_kohei 
 */
@Builder
@Data
public class RegisteredRoomJson implements Json {
    private static final long serialVersionUID = 910139523300534581L;

    private Integer roomId;

    public static RegisteredRoomJson create(Room room) {

        return RegisteredRoomJson.builder().roomId(room.getRoomId().getId()).build();
    }
}
