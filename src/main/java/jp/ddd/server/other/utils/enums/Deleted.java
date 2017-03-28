package jp.ddd.server.other.utils.enums;

public enum Deleted {

  PUBLIC((byte) 0, false),
  DELETED((byte) 1, true);

  private byte code;

  private boolean flag;

  private Deleted(byte code) {
    this.code = code;
  }

  private Deleted(byte code, boolean flag) {
    this.code = code;
    this.flag = flag;
  }

  public byte getCode() {
    return code;
  }

  public boolean getFlag() {
    return flag;
  }

  public static Deleted get(byte code) {
    for (Deleted deleted : values()) {
      if (deleted.getCode() == code) {
        return deleted;
      }
    }
    return null;
  }
}
