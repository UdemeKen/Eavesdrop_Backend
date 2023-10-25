package com.trailblazers.sentimentalanalysis.service;

import com.trailblazers.sentimentalanalysis.dto.requestDto.LoginRequestDto;
import com.trailblazers.sentimentalanalysis.dto.requestDto.SignupRequestDto;
import com.trailblazers.sentimentalanalysis.utils.BaseResponseUtil;

public interface UserService {
    BaseResponseUtil signUp(SignupRequestDto signupRequest);
    Boolean verifyToken(String token);
    BaseResponseUtil login(LoginRequestDto loginRequest);
}
