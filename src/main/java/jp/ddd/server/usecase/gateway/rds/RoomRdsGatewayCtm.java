package jp.ddd.server.usecase.gateway.rds;

import jp.ddd.server.adapter.gateway.rds.entity.RoomRds;
import org.eclipse.collections.api.list.ImmutableList;

import java.util.Optional;

/**
 * Created by noguchi_kohei 
 */
public interface RoomRdsGatewayCtm {

    ImmutableList<RoomRds> findByDtDesc(Integer userId);

    Optional<RoomRds> getOpt(Integer id);

}
