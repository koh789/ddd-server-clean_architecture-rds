package jp.ddd.server.adapter.gateway.redis.base;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * redisにおけるkeyを表現します。
 *
 * @author noguchi_kohei
 */
@AllArgsConstructor
@Value
public class Key implements Serializable {

    private static final long serialVersionUID = 8287223606378011085L;
    private final String key;

}
