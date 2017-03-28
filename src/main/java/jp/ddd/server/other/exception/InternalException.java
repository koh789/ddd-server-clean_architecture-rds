package jp.ddd.server.other.exception;


/**
 * 内部エラーを表現します。
 *
 * @author noguchi_kohei
 */
public class InternalException extends CoreException {
  private static final long serialVersionUID = 1L;

  /**
   * デフォルトコンストラクタ。
   */
  public InternalException() {
    super();

  }

  /**
   * コンストラクタ。
   *
   * @param message 例外メッセージ
   */
  public InternalException(String message) {
    super(message);
  }

  /**
   * コンストラクタ。
   *
   * @param cause 例外
   */
  public InternalException(Throwable cause) {
    super(cause);
  }

  /**
   * コンストラクタ。
   *
   * @param message 例外メッセージ
   * @param cause   例外
   */
  public InternalException(Throwable cause, String message) {
    super(message, cause);
  }

  /**
   * コンストラクタ。
   *
   * @param message       例外メッセージ
   * @param cause         例外
   * @param transitionUrl 遷移URL
   */
  public InternalException(Throwable cause, String message, String transitionUrl) {
    super(message, cause, transitionUrl);
  }

  /**
   * コンストラクタ。
   *
   * @param message        例外メッセージ
   * @param messageForUser ユーザ通知用メッセージ
   */
  public InternalException(String message, String messageForUser) {
    super(message, messageForUser);

  }

  /**
   * コンストラクタ。
   *
   * @param message        例外メッセージ
   * @param messageForUser ユーザ通知用メッセージ
   * @param transitionUrl  遷移URL
   */
  public InternalException(String message, String messageForUser, String transitionUrl) {
    super(message, messageForUser, transitionUrl);
  }

  /**
   * コンストラクタ。
   *
   * @param message        例外メッセージ
   * @param messageForUser ユーザ通知用メッセージ
   * @param cause          例外
   */
  public InternalException(String message, String messageForUser, Throwable cause) {
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
  public InternalException(String message, String messageForUser, String transitionUrl, Throwable cause) {
    super(message, messageForUser, transitionUrl, cause);
  }

  /* (非 Javadoc)
   * @see jp.co.cyberagent.base.CoreException#getInformationLevelType()
   */
  @Override
  public InformationLevelType getInformationLevelType() {
    return InformationLevelType.ERROR;
  }

}
