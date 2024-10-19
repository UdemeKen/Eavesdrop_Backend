package com.trailblazers.sentimentalanalysis.controller;

import com.trailblazers.sentimentalanalysis.dto.requestDto.LoginRequestDto;
import com.trailblazers.sentimentalanalysis.dto.requestDto.SignupRequestDto;
import com.trailblazers.sentimentalanalysis.service.UserService;
import com.trailblazers.sentimentalanalysis.utils.BaseResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;


    @PostMapping("/register")
    public BaseResponseUtil signup(@RequestBody SignupRequestDto signupRequest){
        return userService.signUp(signupRequest);
    }

    @PostMapping("/login")
    public BaseResponseUtil login(@RequestBody LoginRequestDto request) {
        return userService.login(request);
    }
}
