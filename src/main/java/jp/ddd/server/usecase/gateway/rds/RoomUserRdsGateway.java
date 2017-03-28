package jp.ddd.server.usecase.gateway.rds;

import jp.ddd.server.adapter.gateway.rds.entity.RoomUserRds;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by noguchi_kohei 
 */
public interface RoomUserRdsGateway
  extends JpaRepository<RoomUserRds, Integer>, RoomUserRdsGatewayCtm {
}
