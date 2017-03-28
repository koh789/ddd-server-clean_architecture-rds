package jp.ddd.server.adapter.gateway.rds.impl;

import jp.ddd.server.usecase.gateway.rds.RoomUserRdsGatewayCtm;
import jp.ddd.server.adapter.gateway.rds.entity.RoomUserRds;
import jp.ddd.server.other.utils.DsLists;
import jp.ddd.server.other.utils.enums.Status;
import lombok.val;
import org.eclipse.collections.api.list.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.Optional;

/**
 * Created by noguchi_kohei 
 */
@Component
public class RoomUserRdsGatewayImpl implements RoomUserRdsGatewayCtm {
    @Autowired
    private EntityManager em;

    @Override
    public Optional<RoomUserRds> getOptByUnq(Integer roomId, Integer userId) {
        val results = em //
          .createNamedQuery("RoomUser.getByUnq")//
          .setParameter("rid", roomId) //
          .setParameter("uid", userId) //
          .getResultList();

        return DsLists.getFirstOpt(results);
    }

    @Override
    public ImmutableList<RoomUserRds> findByRoomId(Integer roomId) {
        val results = em //
          .createNamedQuery("RoomUser.getByRoomIdAndStatus")//
          .setParameter("rid", roomId) //
          .setParameter("status", Status.VALID) //
          .getResultList();
        return DsLists.toImt(results);
    }
}

