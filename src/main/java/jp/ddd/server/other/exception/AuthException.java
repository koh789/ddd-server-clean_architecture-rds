package jp.ddd.server.other.exception;

/**
 * 認証エラーを表現します
 *
 * @author noguchi_kohei
 */
public class AuthException extends CoreException {
  private static final long serialVersionUID = 1L;

  /**
   * デフォルトコンストラクタ。
   */
  public AuthException() {
    super();

  }

  /**
   * コンストラクタ。
   *
   * @param message 例外メッセージ
   */
  public AuthException(String message) {
    super(message);
  }

  public AuthException(String message, boolean isToUser) {
    super(message, isToUser);
  }

  /**
   * コンストラクタ。
   *
   * @param cause 例外
   */
  public AuthException(Throwable cause) {
    super(cause);
  }

  /**
   * コンストラクタ。
   *
   * @param message 例外メッセージ
   * @param cause   例外
   */
  public AuthException(Throwable cause, String message) {
    super(message, cause);
  }

  /**
   * コンストラクタ。
   *
   * @param message       例外メッセージ
   * @param cause         例外
   * @param transitionUrl 遷移URL
   */
  public AuthException(Throwable cause, String message, String transitionUrl) {
    super(message, cause, transitionUrl);
  }

  /**
   * コンストラクタ。
   *
   * @param message        例外メッセージ
   * @param messageForUser ユーザ通知用メッセージ
   */
  public AuthException(String message, String messageForUser) {
    super(message, messageForUser);

  }

  /**
   * コンストラクタ。
   *
   * @param message        例外メッセージ
   * @param messageForUser ユーザ通知用メッセージ
   * @param transitionUrl  遷移URL
   */
  public AuthException(String message, String messageForUser, String transitionUrl) {
    super(message, messageForUser, transitionUrl);
  }

  /**
   * コンストラクタ。
   *
   * @param message        例外メッセージ
   * @param messageForUser ユーザ通知用メッセージ
   * @param cause          例外
   */
  public AuthException(String message, String messageForUser, Throwable cause) {
    super(message, messageForUser, cause);

  }

  /**
   * コンストラクタ。
   *
   * @param message        例外メッセージ
   * @param messageForUser ユーザ通知用メッセージ
   * @param transitionUrl  遷移URL
   * @param cause          例外
   */
  public AuthException(String message, String messageForUser, String transitionUrl, Throwable cause) {
    super(message, messageForUser, transitionUrl, cause);
  }

  /*
   * (non-Javadoc)
   * @see detox.core.exception.CoreException#getInformationLevelType()
   */
  @Override
  public InformationLevelType getInformationLevelType() {
    return InformationLevelType.WARN;
  }

}
