package me.ian.workoutrecoder.exception;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.log4j.Log4j2;
import me.ian.workoutrecoder.controller.common.RestResponse;
import me.ian.workoutrecoder.enums.ApplicationResponseCodeEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.sasl.AuthenticationException;


@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            HttpMessageNotReadableException.class,
            MethodArgumentNotValidException.class,
            ConstraintViolationException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestResponse handleParamException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        return new RestResponse(ApplicationResponseCodeEnum.PARAMETER_WRONG.getCode(), bindingResult.getAllErrors().get(0).getDefaultMessage());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestResponse handleMissingParameterException(MissingServletRequestParameterException e) {
        return new RestResponse(ApplicationResponseCodeEnum.PARAMETER_WRONG.getCode(), e.getMessage());
    }


    @ExceptionHandler({RestException.class})
    public RestResponse handleRestException(RestException e) {
        return new RestResponse(e.getCode(), e.getMsg());
    }

    @ExceptionHandler({
            ExpiredJwtException.class,
            SignatureException.class,
            AuthenticationException.class
    })
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public RestResponse handleAuthorizationException(Exception e) {
        log.error("Handle error {} \n{}", e.getClass(), e.getMessage());
        return new RestResponse(ApplicationResponseCodeEnum.AUTHENTICATE_FAILED.getCode(), e.getMessage());
    }
}
