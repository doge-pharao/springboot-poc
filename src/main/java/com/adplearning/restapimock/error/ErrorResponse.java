package com.adplearning.restapimock.error;

public class ErrorResponse {

    private int status;
    private String message;

    public ErrorResponse(int status, String message) {
        this.status = status;
         this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "status=" + status +
                ", message=" + message +
                '}';
    }
}