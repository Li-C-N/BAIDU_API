package com.ljd.ai.utils;
import com.ljd.ai.enums.CustomizeCode;
import lombok.Data;

@Data
public class Result<T> {
        private Integer code;
        private String message;
        private T data;



        public static <T> Result<T> errorOf(Integer code, String message,T date) {
            Result<T> result = new Result<>();
            result.setCode(code);
            result.setMessage(message);
            result.setData(date);
            return result;
        }


        public static <T> Result<T> errorOf(CustomizeCode errorCode,T date) {
            return errorOf(errorCode.getCode(), errorCode.getMessage(),date);
        }


    public static <T> Result<T> okOf(Integer code, String message,T date) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        result.setMessage(message);
        result.setData(date);
        return result;
    }


    public static <T> Result<T> okOf(CustomizeCode errorCode,T date) {
        return errorOf(errorCode.getCode(), errorCode.getMessage(),date);
    }

}


