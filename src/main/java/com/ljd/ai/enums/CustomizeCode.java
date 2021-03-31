package com.ljd.ai.enums;

public enum CustomizeCode  {
    UPLOAD_IMG_SUCCESS(2001, "图片上传成功"),
    UPLOAD_IMG_FAILED(2001, "图片上传失败"),
    ;

    private final Integer code;
    private final String message;

    CustomizeCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    public Integer getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
}
