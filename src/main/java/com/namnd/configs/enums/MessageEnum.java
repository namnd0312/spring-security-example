package com.namnd.configs.enums;

public enum MessageEnum {

    INTERNAL_API_ERROR("SEATELLER-500", "INTERNAL SERVER ERROR"),
    BAD_REQUEST("400", "BAD REQUEST"),
    VALIDATE_FAIL("401", "Tham số không hợp lệ"),
    OK("00", "OK"),
    SUCCESS("00", "Success");
    
    private final String code;
    private final String message;

    MessageEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
