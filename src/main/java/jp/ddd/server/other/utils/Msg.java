package jp.ddd.server.other.utils;

/**
 * メッセージを管理します。
 * 後に外部リソースに切り替える。
 *
 * @author noguchi_kohei
 */
public class Msg {

    public final static String INVALID = "不正なリクエストです。";
    public final static String NOT_NULL = "入力必須です。";
    public final static String ONLY_TEL_OR_EMAIL = "電話番号かメールアドレスを入力してください。";
    public final static String ONLY_TEL = "電話番号を入力してください。";
    public final static String ONLY_EN_OR_NUM = "英数字のみ入力してください。";
    public final static String INPUT_3TO30 = "入力は3文字以上３０文字までです。";
    public final static String INPUT_5TO30 = "入力は5文字以上３０文字までです。";
    public final static String INPUT_6TO30 = "入力は6文字以上３０文字までです。";
    public final static String INPUT_8TO30 = "入力は8文字以上３０文字までです。";
    public final static String INPUT_TO10 = "入力は10文字までです。";
    public final static String INPUT_TO15 = "入力は15文字までです。";
    public final static String INPUT_TO30 = "入力は30文字までです。";
    public final static String INPUT_TO50 = "入力は50文字までです。";
    public final static String INPUT_TO200 = "入力は200文字までです。";
    public final static String INPUT_INVALID = "不正な入力値です。";

    public final static String FAIL_AUTH = "認証情報の取得に失敗しました。";
    public final static String FAIL_LOGIN = "ログインに失敗しました。";
    public final static String INVALID_LOGIN_ID = "アカウントが存在しません。";
    public final static String MISMATCH_PASS = "パスワードが一致しません。";
    public final static String NOT_FOUND_USER = "ユーザー情報が見つかりませんでした。";
    public final static String NOT_FOUND_ROOM = "指定されたメッセージルームは存在しません。";
    public final static String FORBIDDEN = "アクセスが拒否されました。";
    public final static String FORBIDDEN_ROOM = "ルームメンバー以外のアクセスは許可されません。";
    public final static String FORBIDDEN_MSG_POST = "メッセージ送信者以外の投稿は許可されません。";
    public final static String FORBIDDEN_MSG_DEL = "メッセージ送信者以外の削除処理は許可されません。";
    public final static String FORBIDDEN_USER = "認証情報と送信情報が一致しません。";
    public final static String EXISTED_LOGIN_ID = "登録済みログインIDです。";
}
