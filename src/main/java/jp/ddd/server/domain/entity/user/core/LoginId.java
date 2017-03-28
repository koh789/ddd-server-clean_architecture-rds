package jp.ddd.server.domain.entity.user.core;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * Created by noguchi_kohei 
 */
@AllArgsConstructor
@Value
public class LoginId implements Serializable {
    private final String id;
}
