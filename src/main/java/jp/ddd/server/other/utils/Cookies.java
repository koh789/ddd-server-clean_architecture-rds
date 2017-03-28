package jp.ddd.server.other.utils;

import jp.ddd.server.other.exception.AuthException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.UUID;

/**
 * クッキー関連
 *
 * @author noguchi_kohei
 */
public class Cookies {
    private static final Logger log = LoggerFactory.getLogger(Requests.class);

    private static final String SESSION_COOKIE_NAME = "JSESSIONID";

    public static String setKey(HttpServletRequest req, HttpServletResponse res) {
        return getKeyOpt(req) //
          .orElseGet(() -> {
              String key = UUID.randomUUID().toString();
              Cookie cookie = new Cookie(SESSION_COOKIE_NAME, key);

              cookie.setSecure(req.isSecure());
              cookie.setMaxAge(Integer.MAX_VALUE);
              cookie.setPath(Strings.defaultIfEmpty(req.getContextPath(), "/"));
              res.addCookie(cookie);
              return key;
          });
    }

    public static Optional<String> getKeyOpt(HttpServletRequest req) {
        Cookie cookie = WebUtils.getCookie(req, SESSION_COOKIE_NAME);
        if (cookie == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(cookie.getValue());
    }

    public static String getKey(HttpServletRequest req) {
        return getKeyOpt(req).orElseThrow(() -> new AuthException("セッションが存在しません。", Msg.NOT_FOUND_USER));
    }

    /**
     * セッション上から認証済みの利用者情報を取り除きます。
     * <p>明示的にクッキーの削除を実施します。動作はブラウザに依存します。</p>
     * <p>ログアウト処理など、認証を取り除くのみの処理で使用してください。<br>
     * ログイン時の既存セッションの破棄はSet-Cookieヘッダが複数になり、サーブレットコンテナのセッション管理で不具合が発生することがあります。(etc. memcached-session-manager)<br>
     */
    public static void terminate(HttpServletRequest req, HttpServletResponse res) {
        Cookie cookie = WebUtils.getCookie(req, SESSION_COOKIE_NAME);
        if (cookie == null) {
            return;
        }
        cookie.setSecure(req.isSecure());
        cookie.setMaxAge(0);
        cookie.setPath(Strings.defaultIfEmpty(req.getContextPath(), "/"));
        res.addCookie(cookie);
    }
}
