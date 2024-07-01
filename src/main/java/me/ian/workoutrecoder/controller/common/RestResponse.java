package me.ian.workoutrecoder.controller.common;

import lombok.Data;

@Data
public class RestResponse<T> {
    private Integer code;

    private String msg;

    private T data;

    public RestResponse(T data) {
        this(1, null, data);
    }

    public RestResponse(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public RestResponse(Integer code, String msg) {
        this(code, msg, null);
    }
}
