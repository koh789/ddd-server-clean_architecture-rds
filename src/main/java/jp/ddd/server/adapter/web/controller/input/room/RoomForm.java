package jp.ddd.server.adapter.web.controller.input.room;

import jp.ddd.server.adapter.web.controller.input.Form;
import jp.ddd.server.other.utils.Msg;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class RoomForm implements Form {
    private static final long serialVersionUID = -310879856818575460L;

    @NotNull(message = Msg.NOT_NULL)
    private String roomName;

    @NotNull(message = Msg.NOT_NULL)
    private List<Integer> joinUserIds;
}
