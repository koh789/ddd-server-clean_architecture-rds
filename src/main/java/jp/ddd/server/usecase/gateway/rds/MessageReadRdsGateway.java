package jp.ddd.server.usecase.gateway.rds;

import jp.ddd.server.adapter.gateway.rds.entity.MessageReadRds;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by noguchi_kohei 
 */
public interface MessageReadRdsGateway
  extends JpaRepository<MessageReadRds, Long>, MessageReadRdsGatewayCtm {
}
