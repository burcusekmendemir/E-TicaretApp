package com.burcu.exception;

import lombok.Getter;

@Getter
public class TryMeAppException extends RuntimeException {

    private final ErrorType errorType;

    public TryMeAppException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public TryMeAppException(ErrorType errorType, String message){
        super(message);
        this.errorType = errorType;
    }


}
