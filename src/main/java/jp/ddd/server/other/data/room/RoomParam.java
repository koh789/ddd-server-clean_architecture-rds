package jp.ddd.server.other.data.room;

import jp.ddd.server.other.data.Dto;
import jp.ddd.server.other.utils.DsLists;
import jp.ddd.server.adapter.web.controller.input.room.RoomForm;
import lombok.Builder;
import lombok.Value;
import org.eclipse.collections.api.list.ImmutableList;

/**
 * Created by noguchi_kohei 
 */
@Builder
@Value
public class RoomParam implements Dto {

    private final Integer createUserId;
    private final String roomName;
    private final ImmutableList<Integer> joinUserIds;

    public static RoomParam create(Integer createUserId, RoomForm roomForm) {
        return RoomParam.builder() //
          .createUserId(createUserId)//
          .roomName(roomForm.getRoomName())//
          .joinUserIds(DsLists.toImt(roomForm.getJoinUserIds())) //
          .build();
    }
}
