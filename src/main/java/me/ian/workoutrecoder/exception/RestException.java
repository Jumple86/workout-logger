package me.ian.workoutrecoder.exception;

import lombok.Data;
import me.ian.workoutrecoder.enums.ErrorCodeEnum;

@Data
public class RestException extends RuntimeException{
    private Integer code;
    private String msg;
    private Object data;

    public RestException(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public RestException(Integer code, String msg) {
        this(code, msg, null);
    }

    public RestException(Integer code) {
        this(code, ErrorCodeEnum.getMsgByCode(code));
    }
}
