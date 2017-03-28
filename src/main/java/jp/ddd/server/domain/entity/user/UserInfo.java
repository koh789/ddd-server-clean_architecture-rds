package jp.ddd.server.domain.entity.user;

import jp.ddd.server.domain.entity.ValueObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

/**
 * Created by noguchi_kohei 
 */
@Builder
@AllArgsConstructor
@Value
public class UserInfo implements ValueObject<UserInfo> {
    private static final long serialVersionUID = -3061170309246132472L;

    private final String name;

    private final String email;

    private final String tel;

}
