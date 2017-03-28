package jp.ddd.server.other.utils;

import jp.ddd.server.other.exception.InternalException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Hash作成クラス
 *
 */
public class Hashes {

    public static String toSHA256(String str) {
        return hash("SHA-256", str);
    }

    public static String toMD5(String str) {
        return hash("MD5", str);
    }

    public static String toMD5(byte[] target) {
        return hash("MD5", target);
    }

    /**
     * 指定文字列に対して指定アルゴリズムでハッシュ値を取得する。
     *
     * @param algorithm アルゴリズム
     * @param str 文字列
     * @return String ハッシュ文字列
     */
    public static String hash(String algorithm, String str) {
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.update(str.getBytes());
            return toHexString(md.digest());

        } catch (NoSuchAlgorithmException e) {
            throw new InternalException(e);
        }
    }

    /**
     * 指定文字列に対して指定アルゴリズムでハッシュ値を取得する。
     *
     * @param algorithm アルゴリズム
     * @param target
     * @return String ハッシュ文字列
     */
    public static String hash(String algorithm, byte[] target) {
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.update(target);
            return toHexString(md.digest());

        } catch (NoSuchAlgorithmException e) {
            throw new InternalException(e);
        }
    }

    /**
     * バイト配列を16進数文字列に変換する。
     *
     * @param b バイト配列
     * @return String 16進数文字列
     */
    public static final String toHexString(byte[] b) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            String tmp = Integer.toHexString(b[i] & 0xff);
            if (tmp.length() == 1) {
                buffer.append('0').append(tmp);
            } else {
                buffer.append(tmp);
            }
        }
        return buffer.toString();
    }
}