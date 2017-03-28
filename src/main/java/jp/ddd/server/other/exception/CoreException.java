package jp.ddd.server.other.exception;

/**
 * サービス固有例外時に継承して使用してください。
 *
 * @author noguchi_kohei
 */

public abstract class CoreException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * ユーザ通知用メッセージ
     */
    private String messageForUser;
    /**
     * 遷移URL
     */
    private String transitionUrl;

    /**
     * デフォルトコンストラクタ。
     */
    public CoreException() {
        super();

    }

    /**
     * コンストラクタ。
     *
     * @param message 例外メッセージ
     */
    public CoreException(String message) {
        super(message);
    }

    public CoreException(String message, boolean isToUser) {
        super(message);
        if (isToUser) {
            messageForUser = message;
        }
    }

    /**
     * コンストラクタ。
     *
     * @param cause 例外
     */
    public CoreException(Throwable cause) {
        super(cause);
    }

    /**
     * コンストラクタ。
     *
     * @param message 例外メッセージ
     * @param cause   例外
     */
    public CoreException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * コンストラクタ。
     *
     * @param message       例外メッセージ
     * @param cause         例外
     * @param transitionUrl 遷移URL
     */
    public CoreException(String message, Throwable cause, String transitionUrl) {
        super(message, cause);
        this.transitionUrl = transitionUrl;
    }

    /**
     * コンストラクタ。
     *
     * @param message        例外メッセージ
     * @param messageForUser ユーザ通知用メッセージ
     */
    public CoreException(String message, String messageForUser) {
        super(message);
        this.messageForUser = messageForUser;
    }

    /**
     * コンストラクタ。
     *
     * @param message        例外メッセージ
     * @param messageForUser ユーザ通知用メッセージ
     * @param transitionUrl  遷移URL
     */
    public CoreException(String message, String messageForUser, String transitionUrl) {
        super(message);
        this.messageForUser = messageForUser;
        this.transitionUrl = transitionUrl;
    }

    /**
     * コンストラクタ。
     *
     * @param message        例外メッセージ
     * @param messageForUser ユーザ通知用メッセージ
     * @param cause          例外
     */
    public CoreException(String message, String messageForUser, Throwable cause) {
        super(message, cause);
        this.messageForUser = messageForUser;
    }

    /**
     * コンストラクタ。
     *
     * @param message        例外メッセージ
     * @param messageForUser ユーザ通知用メッセージ
     * @param transitionUrl  遷移URL
     * @param cause          例外
     */
    public CoreException(String message, String messageForUser, String transitionUrl, Throwable cause) {
        super(message, cause);
        this.transitionUrl = transitionUrl;
        this.messageForUser = messageForUser;
    }

    /**
     * ユーザ通知用メッセージを取得します。
     *
     * @return ユーザ通知用メッセージ
     */
    public String getMessageForUser() {
        return messageForUser;
    }

    public String getTransitionUrl() {
        return transitionUrl;
    }

    /**
     * 例外の情報レベル種別を返します。
     *
     * @return 情報レベル種別
     */
    public abstract InformationLevelType getInformationLevelType();

    /**
     * 情報レベル種別を表現します。
     * <p>Log4jなどのログレベルの概念とほぼ同じです。</p>
     *
     * @author kadota_noriaki
     */
    public enum InformationLevelType {
        /**
         * 致命的なエラー情報
         */
        FATAL,
        /**
         * エラー情報
         */
        ERROR,
        /**
         * 警告情報
         */
        WARN,
        /**
         * 一般情報
         */
        INFO,
        /**
         * デバッグ情報
         */
        DEBUG,
        /**
         * より詳細なデバッグ情報
         */
        TRACE;

    }
}