package jp.ddd.server.other.utils;

/**
 * 正規表現を管理します。
 *
 * @author noguchi_kohei
 */
public class Regex {

  public final static String EMAIL = "^[a-zA-Z0-9_\\.\\-\\+]+?@[A-Za-z0-9_\\.\\-]+$";
  public final static String TEL = "^[0-9]{3}-?[0-9]{4}-?[0-9]{4}$";
  public final static String EMAIL_AND_TEL = EMAIL + "|" + TEL;
  public final static String PASSWORD = "^[a-zA-Z0-9_\\-]*$";
  public final static String SPACE = "\\ ";
  public final static String HYPHEN_OR_SPACE = "-|\\ ";
}
