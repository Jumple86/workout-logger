package me.ian.workoutrecoder.enums;

import lombok.Getter;

@Getter
public enum ApplicationResponseCodeEnum {
    SYSTEM_ERROR(10000, "System error"),
    PARAMETER_WRONG(10001, "Parameter wrong"),
    OPERATION_FAILED(10002, "Operation failed"),
    ACCOUNT_ALREADY_EXIST(10003, "Account is already exist"),
    ACTION_ALREADY_EXIST(10004, "Action is already exist"),
    USER_NOT_EXIST(10005, "User is not exist, please register"),
    PASSWORD_WRONG(10006, "Password is wrong"),
    AUTHENTICATE_FAILED(10007, "Authentication illegal"),
    ALREADY_CREATED_WEEKLY_MENU(10008, "Already created weekly menu"),
    MENU_ALREADY_EXIST(10009, "Menu is already exist"),
    DATE_NOT_SAME(10010, "Record date not same"),
    SET_NO_NOT_SAME(10011, "Set number can not same"),
    DATA_NOT_EXIST(11000, "Data not exist");

    private int code;
    private String msg;

    ApplicationResponseCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getMsgByCode(int code) {
        for (ApplicationResponseCodeEnum applicationResponseCodeEnum : ApplicationResponseCodeEnum.values()) {
            if (code == applicationResponseCodeEnum.getCode()) {
                return applicationResponseCodeEnum.getMsg();
            }
        }
        return null;
    }
}
