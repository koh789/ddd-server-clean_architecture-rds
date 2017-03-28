package jp.ddd.server.other.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * セッション関連
 *
 * @author noguchi_kohei
 */
public class Requests {
    private static final Logger log = LoggerFactory.getLogger(Requests.class);

    /**
     * セッションIDが格納されているCookie名
     */
    private static final String SESSION_COOKIE_NAME = "JSESSIONID";

    public static void terminateSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            log.debug("terminate session. sessionId : ", session.getId());
            try {
                session.invalidate();
            } catch (IllegalStateException e) {
                // 例外は上位にthrowしない。
            }
        }
    }

    /**
     * クライアントの送信元IPアドレスをリクエスト情報から取得します。
     * <p>{@code X-FORWARDED-FOR}ヘッダに対応しています。</p>
     *
     * @param request リクエスト
     * @return クライアントの送信元IPアドレス
     */
    public static String getRemoteAddress(HttpServletRequest request) {
        // HTTP-ProxyまたはLoadBalancer経由の場合は、以下のヘッダに格納されるため、まず調べる。
        String xForwardedFor = request.getHeader("x-forwarded-for");
        // 取れなかった場合は、リクエストの情報をそのまま使用する。
        if (Strings.isNotEmpty(xForwardedFor)) {
            return xForwardedFor;
        }
        return request.getRemoteAddr();
    }

}
