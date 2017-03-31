package jp.ddd.server.adapter.web.presenter.output.room;

import jp.ddd.server.adapter.web.presenter.output.Json;
import jp.ddd.server.other.utils.Dates;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * Created by noguchi_kohei 
 */
@Builder
@Data
public class RegisteredRoomUserJson implements Json {
    private static final long serialVersionUID = -6715594480633791675L;

    private Long roomUserId;

    private Integer userId;

    private String joinDt;

    public static RegisteredRoomUserJson create(Long roomUserId, Integer userId, Date joinDt) {
        return RegisteredRoomUserJson.builder().roomUserId(roomUserId).userId(userId).joinDt(Dates.toString(joinDt)).build();
    }
}
