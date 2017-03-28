package jp.ddd.server.domain.entity.user.core;

import jp.ddd.server.other.utils.Hashes;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * Created by noguchi_kohei 
 */
@AllArgsConstructor
@Value
public class HashPass implements Serializable {
    private static final long serialVersionUID = -5977804619502988648L;

    private final String pass;

    public static HashPass create(String hashPass) {
        return new HashPass(hashPass);
    }

    public static HashPass createByRawPass(String rawPass) {
        return new HashPass(Hashes.toSHA256(rawPass));
    }
}
