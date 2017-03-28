package jp.ddd.server.other.utils;

import org.eclipse.collections.api.map.ImmutableMap;

import java.util.Optional;

public class DsMaps {

    public static <K, V> Optional<V> getOpt(ImmutableMap<K, V> map, K key) {
        return Optional.ofNullable(map.get(key));
    }
}
