package jp.ddd.server.adapter.web.interceptor;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import jp.ddd.server.other.utils.Cookies;
import jp.ddd.server.other.utils.Requests;

public class IOInterceptor extends HandlerInterceptorAdapter {
  private final Logger log = LoggerFactory.getLogger(this.getClass());

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
    throws Exception {
    Cookies.setKey(request, response);

    log.info("[request]URL -> " + request.getMethod() + " " + request.getRequestURI() + " "
      + request.getQueryString());
    log.info("[request]media-type  -> " + request.getContentType());

    if (request.getCookies() != null) {
      Arrays.asList(request.getCookies()).stream().forEach(cookie -> {
        log.info("[request]cookie -> (name, value): [ " + cookie.getName() + ", " + cookie.getValue() + " ]");
      });
    }
    log.info("[request]ip -> " + Requests.getRemoteAddress(request));

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
