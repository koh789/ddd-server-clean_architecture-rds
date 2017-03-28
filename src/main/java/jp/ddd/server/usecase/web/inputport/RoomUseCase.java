package jp.ddd.server.usecase.web.inputport;

import jp.ddd.server.domain.entity.room.Room;
import jp.ddd.server.domain.entity.user.core.UserId;
import org.eclipse.collections.api.list.ImmutableList;

/**
 * ユーザのUseCaseに関するInPutPortです。
 * Created by noguchi_kohei
 */
public interface RoomUseCase {

    Room register(String loginSessionId, String roomName, ImmutableList<UserId> joinUserIds);
}
