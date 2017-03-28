package jp.ddd.server.usecase.gateway.rds;

import jp.ddd.server.adapter.gateway.rds.entity.UserRds;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by noguchi_kohei 
 */
public interface UserRdsGateway extends JpaRepository<UserRds, Integer>, UserRdsGatewayCtm {
}
