package jp.ddd.server.other.utils.enums;

public enum Status {

    INVALID((byte) 0),
    VALID((byte) 1);

    private byte code;

    private Status(byte code) {
        this.code = code;
    }

    public byte getCode() {
        return code;
    }

    public static Status get(byte code) {
        for (Status deleted : values()) {
            if (deleted.getCode() == code) {
                return deleted;
            }
        }
        return null;
    }
}
