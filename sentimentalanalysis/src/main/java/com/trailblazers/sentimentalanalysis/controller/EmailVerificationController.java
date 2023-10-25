package com.trailblazers.sentimentalanalysis.controller;

import com.trailblazers.sentimentalanalysis.dto.responseDto.HttpResponseDto;
import com.trailblazers.sentimentalanalysis.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/verification")
public class EmailVerificationController {

    private final UserService userService;

    @GetMapping("/verify")
    public ResponseEntity<HttpResponseDto> userAccountVerification(@RequestParam("token") String token){
        Boolean isVerified = userService.verifyToken(token);
        return ResponseEntity.ok(
                HttpResponseDto.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("Success", isVerified))
                        .message("Account Verified")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }
}
