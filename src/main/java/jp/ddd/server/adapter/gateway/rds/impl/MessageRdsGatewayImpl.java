package jp.ddd.server.adapter.gateway.rds.impl;

import jp.ddd.server.usecase.gateway.rds.MessageRdsGatewayCtm;
import jp.ddd.server.adapter.gateway.rds.entity.MessageRds;
import jp.ddd.server.other.data.common.Page;
import jp.ddd.server.other.utils.DsLists;
import jp.ddd.server.other.utils.enums.Status;
import lombok.val;
import org.eclipse.collections.api.list.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

/**
 * Created by noguchi_kohei 
 */
@Component
public class MessageRdsGatewayImpl implements MessageRdsGatewayCtm {
    @Autowired
    private EntityManager em;

    @Override
    public ImmutableList<MessageRds> findByRoomId(Integer roomId, Page page) {

        val results = em //
          .createNamedQuery("Message.findWithReadByRoomIdOrderByIdDesc")//
          .setParameter("rid", (Integer) roomId)//
          .setFirstResult(page.getOffset())//
          .setMaxResults(page.getLimit()) //
          .setParameter("status", Status.VALID) //
          .getResultList();
        return DsLists.toImt(results);
    }

    @Override
    public ImmutableList<MessageRds> findUnread(Integer roomId, Integer userId) {
        val results = em.createNamedQuery("Message.findUnreadByRidAndUid")//
          .setParameter("rid", roomId)//
          .setParameter("uid", userId)//
          .setParameter("status", Status.VALID).getResultList();
        return DsLists.toImt(results);
    }
}
