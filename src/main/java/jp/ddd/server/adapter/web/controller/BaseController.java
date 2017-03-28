package jp.ddd.server.adapter.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ddd.server.other.exception.IllegalDataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jp.ddd.server.other.exception.AccessPermissonException;
import jp.ddd.server.other.exception.AuthException;
import jp.ddd.server.other.exception.CoreException;
import jp.ddd.server.other.exception.NotFoundException;

/**
 * jsonAPI用エラーハンドラー基底クラス。
 *
 * @author noguchi_kohei
 */
public abstract class BaseController {
  protected final Logger log = LoggerFactory.getLogger(getClass());

  @ExceptionHandler(Exception.class)
  public void handleException(Exception e, HttpServletRequest req, HttpServletResponse res) {
    logError(e);

    String msgForUser = null;
    if (e instanceof CoreException) {
      msgForUser = ((CoreException) e).getMessageForUser();

      if (e instanceof IllegalDataException) {
        res.setStatus(HttpStatus.BAD_REQUEST.value());
        setRedirect(res, "/errors/400.html");
      } else if (e instanceof AuthException) {
        res.setStatus(HttpStatus.UNAUTHORIZED.value());
        setRedirect(res, "/auth/login");
      } else if (e instanceof AccessPermissonException) {

        res.setStatus(HttpStatus.FORBIDDEN.value());
        setRedirect(res, "/errors/403.html");
      } else if (e instanceof NotFoundException) {

        res.setStatus(HttpStatus.NOT_FOUND.value());
        setRedirect(res, "/errors/404.html");
      } else {

        res.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        setRedirect(res, "/errors/500.html");
      }
    } else {
      res.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
      setRedirect(res, "/errors/500.html");
    }
  }

  private void setRedirect(HttpServletResponse res, String location) {
    try {
      res.sendRedirect(location);
    } catch (IOException e) {
      logError(e);
    }
  }

  private void logError(Exception e) {
    String causeMsg = e.getCause() == null ? "" : e.getCause().getMessage();
    log.error("[ className ]: " + e.getClass().getName() + " \n [ msg ]: " + e.getMessage() + "  " + causeMsg
      + " \n[ e ]: " + e + " \n [ cause ]: " + e.getCause());
  }

}
