package jp.ddd.server.usecase.gateway.rds;

import jp.ddd.server.adapter.gateway.rds.entity.MessageRds;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by noguchi_kohei 
 */
public interface MessageRdsGateway
  extends JpaRepository<MessageRds, Long>, MessageRdsGatewayCtm {
}
