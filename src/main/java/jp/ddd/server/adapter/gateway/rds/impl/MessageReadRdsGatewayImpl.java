package jp.ddd.server.adapter.gateway.rds.impl;

import jp.ddd.server.usecase.gateway.rds.MessageReadRdsGatewayCtm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

/**
 * Created by noguchi_kohei 
 */
@Component
public class MessageReadRdsGatewayImpl implements MessageReadRdsGatewayCtm {
    @Autowired
    private EntityManager em;

}
