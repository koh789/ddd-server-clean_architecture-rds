package jp.ddd.server.adapter.gateway.redis.base;

import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * redis基底クラスです。
 *
 * @param <T>
 * @author noguchi_kohei
 */
@Repository
public class RedisMapper<T extends Serializable> {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RedisTemplate<Key, T> template;

    /**
     * デフォルトexpire(seconds)
     */
    public final long defaultExpire = 24 * 60 * 60;

    /**
     * キー生成
     * entity毎にユニーク性を保持するためにentityClass名を
     * 同一entity内で複数キーが存在する場合はnameSpaceを使用してください。
     * ex) PostCheer.class, postId:10かつactionUserId:23の場合
     * key = PostCheer_postId<->actionUserId_10<->23
     * そうでない場合nameSpaceは使用しなくても構いません。
     *
     * @param entityClass set対象のdto
     * @param nameSpace   上記参照、必要ない場合getMethod名など各entityで適宜指定してください。
     * @param identifier          　キー
     * @return
     */
    public Key generateStrKey(Class<T> entityClass, String nameSpace, Serializable identifier) {
        return new Key(entityClass.getSimpleName() + "_" + nameSpace + "_" + identifier.toString());
    }

    /**
     * expireをデフォルトを使用する場合コチラを使用してください。
     */
    public T save(Key key, T obj) {
        template.opsForValue().set(key, obj, defaultExpire, TimeUnit.SECONDS);
        return obj;
    }

    public T save(Class<T> entityClass, String nameSpace, Serializable identifier, T obj) {
        val key = generateStrKey(entityClass, nameSpace, identifier);
        template.opsForValue().set(key, obj, defaultExpire, TimeUnit.SECONDS);
        return obj;
    }

    public T save(Key key, T obj, long expire) {
        template.opsForValue().set(key, obj, expire, TimeUnit.SECONDS);
        return obj;
    }

    public T save(Class<T> entityClass, String nameSpace, Serializable identifier, T obj, long expire) {
        val key = generateStrKey(entityClass, nameSpace, identifier);
        template.opsForValue().set(key, obj, expire, TimeUnit.SECONDS);
        return obj;
    }

    public Optional<T> getOpt(Key key) {
        return Optional.ofNullable(template.opsForValue().get(key));
    }

    public Optional<T> getOpt(Class<T> entityClass, String nameSpace, Serializable identifier) {
        val key = generateStrKey(entityClass, nameSpace, identifier);
        return getOpt(key);
    }

    public void delete(Key key) {
        template.delete(key);
    }

    public void delete(Class<T> entityClass, String nameSpace, Serializable identifier) {
        template.delete(generateStrKey(entityClass, nameSpace, identifier));
    }
}
