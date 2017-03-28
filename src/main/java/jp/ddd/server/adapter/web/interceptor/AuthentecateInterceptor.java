package jp.ddd.server.adapter.web.interceptor;

import jp.ddd.server.domain.repository.UserRepository;
import jp.ddd.server.other.annotation.NotLoginRequired;
import jp.ddd.server.other.exception.AuthException;
import jp.ddd.server.other.utils.Cookies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 認証インターセプター
 * 現状認証情報がない場合は全てログインページへ
 *
 * @author noguchi_kohei
 */
public class AuthentecateInterceptor extends HandlerInterceptorAdapter {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        //固有クッキーセット
        Cookies.setKey(req, res);
        NotLoginRequired notLoginRequired = ((HandlerMethod) handler).getMethodAnnotation(NotLoginRequired.class);
        if (notLoginRequired != null) {
            return true;
        }
        if (!userRepository.isLogin(Cookies.getKey(req))) {
            throw new AuthException("login required!");
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e)
      throws Exception {
    }
}
