package jp.ddd.server.usecase.gateway.rds;

import jp.ddd.server.adapter.gateway.rds.entity.RoomUserRds;
import org.eclipse.collections.api.list.ImmutableList;

import java.util.Optional;

/**
 * Created by noguchi_kohei 
 */
public interface RoomUserRdsGatewayCtm {

    Optional<RoomUserRds> getOptByUnq(Integer roomId, Integer userId);

    ImmutableList<RoomUserRds> findByRoomId(Integer roomId);
}
