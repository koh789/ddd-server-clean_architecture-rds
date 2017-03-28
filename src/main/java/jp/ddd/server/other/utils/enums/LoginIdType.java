package jp.ddd.server.other.utils.enums;

import java.util.regex.Pattern;

import jp.ddd.server.other.exception.InternalException;
import jp.ddd.server.other.utils.Regex;

public enum LoginIdType {

  TEL((byte) 1, "tel"),
  EMAIL((byte) 2, "eMail");

  private byte code;

  private String value;

  private LoginIdType(byte code, String value) {
    this.code = code;
    this.value = value;
  }

  public byte getCode() {
    return this.code;
  }

  public String getValue() {
    return this.value;
  }

  public static LoginIdType get(byte code) {
    for (LoginIdType loginIdType : values()) {
      if (loginIdType.getCode() == code) {
        return loginIdType;
      }
    }
    throw new InternalException("LoginIdTypeの判定に失敗しました。" + String.valueOf(code));
  }

  /**
   * LoginIdTypeをコンテキスト内容から判定します。
   */
  public static LoginIdType getBy(String context) {
    if (Pattern.matches(Regex.EMAIL, context)) {
      return LoginIdType.EMAIL;
    } else if (Pattern.matches(Regex.TEL, context)) {
      return LoginIdType.TEL;
    }
    throw new InternalException("想定外の文字列です。" + context);
  }

  public static String getContext(String context) {
    LoginIdType loginIdType = getBy(context);
    if (EMAIL == loginIdType) {
      return context.replaceAll(Regex.SPACE, "");
    } else if (TEL == loginIdType) {
      return context.replaceAll(Regex.HYPHEN_OR_SPACE, "");
    }
    throw new InternalException("想定外の文字列です。" + context);
  }
}
