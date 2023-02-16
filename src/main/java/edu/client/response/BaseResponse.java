package edu.client.response;

import lombok.Data;

@Data
public class BaseResponse {
    protected boolean success;
    protected String message;
}
