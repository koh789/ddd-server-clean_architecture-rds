package jp.ddd.server.other.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.net.URLCodec;

/**
 * 文字列操作系処理
 *
 * @author noguchi_kohei
 */
public final class Strings {

  /**
   * コンストラクタ
   */
  private Strings() {
    // インスタンス生成不可
  }

  public static boolean isEmpty(String str) {
    return (str == null || "".equals(str));
  }

  public static boolean isNotEmpty(String str) {
    return (str != null && !"".equals(str));
  }

  public static String defaultIfEmpty(String str, String replaceStr) {
    return isNotEmpty(str) ? str : replaceStr;
  }

  public static Integer convertNull(Integer num) {
    if (num == null) {
      return 0;
    } else {
      return num;
    }
  }

  /**
   * URLと判別される文字列についてはアンカータグ（<a>タグ）を付加する)
   *
   * @param str 対象文字列
   * @return 変換後の文字列
   */
  public static String addAnchor(String str) {
    return addAnchor(str, null);
  }

  /**
   * URLと判別される文字列についてはアンカータグ（<a>タグ）を付加する) ただし、タグ中のURLにはタグを付加しない
   *
   * @param str    対象文字列
   * @param target target属性の値
   * @return 変換後の文字列
   */
  public static String addAnchor(String str, Map<String, String> attributes) {
    if (str == null) {
      return str;
    }

    String urlCondition = "(http://|https://){1}[\\w\\?\\&\\=\\%\\~\\.\\-/:;#,]+";
    str = str.replaceAll("&nbsp;", " ");
    StringBuilder attribute = new StringBuilder();
    if (attributes != null) {
      for (Map.Entry<String, String> entry : attributes.entrySet()) {
        attribute.append(" ");
        attribute.append(entry.getKey());
        attribute.append("=\"");
        attribute.append(entry.getValue());
        attribute.append("\"");
      }
    }

    StringBuilder buffer = new StringBuilder();
    StringBuilder literalBuffer = new StringBuilder();
    char[] chars = str.toCharArray();
    boolean isTag = false;
    for (int i = 0; i < chars.length; i++) {
      if (chars[i] == '<') {
        isTag = true;
        if (literalBuffer.toString().trim().length() > 0) {
          buffer.append(literalBuffer.toString().replaceAll(urlCondition,
            "<a href=\"$0\"" + attribute.toString() + " target=\"_blank\">$0</a>"));
        }
        buffer.append(chars[i]);

        literalBuffer = new StringBuilder();
      } else if (chars[i] == '>') {
        isTag = false;
        buffer.append(chars[i]);
      } else if (isTag) {
        buffer.append(chars[i]);
      } else if (!isTag) {
        literalBuffer.append(chars[i]);
      }
    }
    if (literalBuffer.toString().trim().length() > 0) {
      buffer.append(literalBuffer.toString().replaceAll(urlCondition,
        "<a href=\"$0\"" + attribute.toString() + " target=\"_blank\">$0</a>"));
    }

    return buffer.toString();
  }

  /**
   * 渡された文字列からアンカータグを除去します。
   *
   * @param str
   * @return
   */
  public static String removeAnchor(String str) {

    if (str == null) {
      return str;
    }

    Pattern pattern1 = Pattern.compile("<a.*?>", (Pattern.CASE_INSENSITIVE) | (Pattern.UNICODE_CASE));
    Matcher mc1 = pattern1.matcher(str);
    str = mc1.replaceAll("");

    Pattern pattern2 = Pattern.compile("</a.*?>", (Pattern.CASE_INSENSITIVE) | (Pattern.UNICODE_CASE));
    Matcher mc2 = pattern2.matcher(str);
    str = mc2.replaceAll("");
    return str;
  }

  /**
   * 指定整数を0埋めする。
   *
   * @param val   整数
   * @param digit 0埋め後の桁数
   * @return val.length >= digit であれば val の文字列表現 val.length < digit であれば0埋めした文字列
   */
  public static String paddingZero(int val, int digit) {

    String str = "" + val;
    int length = digit - str.length();
    if (length <= 0) return str;

    StringBuffer buf = new StringBuffer(length).append('0');
    for (int i = 1; i < length; i++) {
      buf.append('0');
    }

    return buf + str;
  }

  /**
   * エンコードされたパラメータをデコードする。<br />
   * 例外が発生した場合は空のHashMapを返す。
   *
   * @param str
   * @param enc
   * @param delim
   * @return
   */
  public static Map<String, String> decodeParam(String str, String enc, String delim) {
    Map<String, String> paramMap = new HashMap<String, String>();
    try {
      StringTokenizer tokens = new StringTokenizer(str, delim);
      while (tokens.hasMoreTokens()) {
        String buf = tokens.nextToken();
        int index = buf.indexOf('=');
        if (index == -1) {
          throw new Exception();
        } else {
          String name = buf.substring(0, index);
          String value = buf.substring(index + 1);
          // value = URLDecoder.decode(value, enc);
          URLCodec codec = new URLCodec();
          value = codec.decode(value, enc);
          paramMap.put(name, value);
        }
      }
    } catch (Exception e) {
      // nothing to do.
    }
    return paramMap;
  }

  /**
   * 渡された文字列が数値かどうか判断する
   *
   * @param number
   * @return
   */
  public static boolean checkInteger(String number) {
    try {
      Integer.parseInt(number);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  /**
   * 文字列から正規表現で特定部分を抽出
   *
   * @param regex
   * @param target
   * @return
   */
  public static String extractMatchString(String regex, String target) {
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(target);
    if (matcher.find()) {
      return matcher.group(1);
    } else {
      throw new IllegalStateException("No match found.");
    }
  }

  /**
   * 渡された文字列からHTMLタグを除去
   *
   * @param str
   * @return
   */
  public static String removehtmlTag(String str) {
    return str.replaceAll("<.+?>", "");
  }

  /**
   * 渡された文字列の文字数をカウントし、指定数以下かどうか判定します。
   * 半角の濁点、半濁点付きの文字は2文字としてカウントされてしまうので
   * 注意してください。
   *
   * @param str      文字列
   * @param maxCount 文字数指定
   * @return
   */
  public static Boolean checkStringCount(String str, Integer maxCount) {
    if (str.length() <= maxCount) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * 文字列を連結します。
   *
   * @param previousStr
   * @param subsequentStr
   * @return
   */
  public static String joinString(String previousStr, String subsequentStr) {
    StringBuffer sb = new StringBuffer();
    sb.append(previousStr);
    sb.append(subsequentStr);
    return sb.toString();
  }

  public static String joinThreeString(String firstStr, String secondStr, String thirdStr) {
    String str = joinString(firstStr, secondStr);
    return joinString(str, thirdStr);
  }

  /**
   * パスワードを生成します。
   */
  public static String hashPass(String pass, String id) {
    return DigestUtils.sha256Hex(pass + DigestUtils.sha256Hex(id));
  }

  public static String getTelWithTrim(String context) {
    return context.replaceAll("-|\\ ", "");
  }

  public static String getEmailWithTrim(String context) {
    return context.replaceAll("\\ ", "");
  }
}
