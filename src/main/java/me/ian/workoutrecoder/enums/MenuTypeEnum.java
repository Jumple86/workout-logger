package me.ian.workoutrecoder.enums;

public enum MenuTypeEnum {
    CUSTOM(1),
    WEEKLY(2);
    private int code;

    MenuTypeEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
