package jp.ddd.server.usecase.gateway.rds;

import jp.ddd.server.adapter.gateway.rds.entity.RoomRds;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by noguchi_kohei 
 */
public interface RoomRdsGateway extends JpaRepository<RoomRds, Integer>, RoomRdsGatewayCtm {
}
