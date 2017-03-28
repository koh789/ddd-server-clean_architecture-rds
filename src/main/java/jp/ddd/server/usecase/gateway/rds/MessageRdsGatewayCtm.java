package jp.ddd.server.usecase.gateway.rds;

import jp.ddd.server.adapter.gateway.rds.entity.MessageRds;
import jp.ddd.server.other.data.common.Page;
import org.eclipse.collections.api.list.ImmutableList;

/**
 * Created by noguchi_kohei 
 */
public interface MessageRdsGatewayCtm {

    ImmutableList<MessageRds> findByRoomId(Integer roomId, Page page);

    /**
     * 指定メッセージルーム内で対象ユーザの未読リストを取得します。
     * @param roomId
     * @param userId
     * @return
     */
    ImmutableList<MessageRds> findUnread(Integer roomId, Integer userId);
}
