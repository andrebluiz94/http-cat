package com.httpcat.generic.expcetion;

public class GetResponseException extends RuntimeException {
    public GetResponseException(String MSG) {
        super(MSG);
    }
}
