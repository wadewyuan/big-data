package com.citictel.bigdata.constants;

public enum StatusCodeEnum {

    SUCCESS(10200, "Success"),
    CREATED(10201, "Created"),
    NO_CONTENT(10204, "No content"),
    INVALID_TOKEN(10401, "Invalid token"),
    INSUFFICIENT_PRIVILEGES(10403, "Insufficient privileges"),
    NOT_FOUND(10404, "Not found"),
    INTERNAL_SERVER_ERROR(10500, "Internal server error")
    ;

    private final Object[] values;

    StatusCodeEnum(Object... vals) {
        this.values = vals;
    }


    public int code() {
        return (int) values[0];
    }

    public String message() {
        return (String) values[1];
    }
}
