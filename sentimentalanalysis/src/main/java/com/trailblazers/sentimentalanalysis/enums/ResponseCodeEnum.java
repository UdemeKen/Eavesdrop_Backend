package com.trailblazers.sentimentalanalysis.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
public enum ResponseCodeEnum {
    SUCCESS(0, "Success"),
    ERROR(-1, "An error occurred. Error message : ${errorMessage}"),
    ERROR_EMAIL_INVALID(-3, "Invalid email address"),
    ERROR_PASSWORD_MISMATCH(-4, "Password does not match"),
    ERROR_DUPLICATE_USER(-5, "User already exist"),
    USER_NOT_FOUND(-8, "Email does not exist"),
    ;

    private final int code;
    private final String description;

}
