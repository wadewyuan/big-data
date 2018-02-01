package com.citictel.bigdata.domain;

import com.citictel.bigdata.constants.StatusCodeEnum;

public class ResponseData {
    private String message;
    private int code;
    private boolean status;
    private Object data;

    public ResponseData() {
        this.setStatusCodeAndMsg(StatusCodeEnum.SUCCESS);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setStatusCodeAndMsg(StatusCodeEnum status) {
        this.code = status.code();
        this.message = status.message();
        switch (status) {
            case CREATED:
                this.status = true;
                break;
            case SUCCESS:
                this.status = true;
                break;
            default:
                this.status = false;
                break;
        }
    }
}
