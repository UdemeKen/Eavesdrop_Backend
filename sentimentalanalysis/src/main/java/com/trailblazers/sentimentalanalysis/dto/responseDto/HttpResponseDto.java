package com.trailblazers.sentimentalanalysis.dto.responseDto;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HttpResponseDto {
    private String timeStamp;
    private int statusCode;
    private HttpStatus status;
    private String message;
    private String developerMessage;
    private String path;
    private String requestMethod;
    private Map<?, ?> data;
}
