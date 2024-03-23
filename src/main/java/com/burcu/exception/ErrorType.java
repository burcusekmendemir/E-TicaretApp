package com.burcu.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ErrorType {

    INTERNAL_ERROR(1000, "Sunucuda beklenmeyen hata oluştu, lütfen tekrar deneyin", HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REEQUEST_ERROR(1001,"Girilen parametreler hatalıdır, lütfen tekrar deneyin", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(1002,"Kullanıcı bulunamadı, lütfen tekrar deneyin", HttpStatus.NOT_FOUND),;



    int code;
    String message;
    HttpStatus httpStatus;


}
