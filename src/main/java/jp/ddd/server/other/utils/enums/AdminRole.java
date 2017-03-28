package jp.ddd.server.other.utils.enums;

public enum AdminRole {

  /**
   * システム管理者（ルート権限）
   */
  SYSTEM_ROOT_ADMIN((byte) 1),
  /**
   * システム管理者
   */
  SYSTEM_ADMIN((byte) 2);

  private final byte code;

  private AdminRole(byte code) {
    this.code = code;
  }

  public byte getCode() {
    return this.code;
  }

  public static AdminRole get(byte code) {
    for (AdminRole adminRole : values()) {
      if (adminRole.getCode() == code) {
        return adminRole;
      }
    }
    return null;
  }

}
