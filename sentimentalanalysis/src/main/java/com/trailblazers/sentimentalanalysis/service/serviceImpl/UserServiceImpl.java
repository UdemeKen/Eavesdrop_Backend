package com.trailblazers.sentimentalanalysis.service.serviceImpl;

import com.trailblazers.sentimentalanalysis.dto.requestDto.LoginRequestDto;
import com.trailblazers.sentimentalanalysis.dto.requestDto.SignupRequestDto;
import com.trailblazers.sentimentalanalysis.dto.responseDto.AuthenticationResponse;
import com.trailblazers.sentimentalanalysis.dto.responseDto.LoginResponseDto;
import com.trailblazers.sentimentalanalysis.enums.ResponseCodeEnum;
import com.trailblazers.sentimentalanalysis.enums.Role;
import com.trailblazers.sentimentalanalysis.model.Verification;
import com.trailblazers.sentimentalanalysis.model.User;
import com.trailblazers.sentimentalanalysis.repository.VerificationRepository;
import com.trailblazers.sentimentalanalysis.repository.UserRepository;
import com.trailblazers.sentimentalanalysis.service.EmailService;
import com.trailblazers.sentimentalanalysis.service.UserService;
import com.trailblazers.sentimentalanalysis.utils.ApplicationUtil;
import com.trailblazers.sentimentalanalysis.utils.BaseResponseUtil;
import com.trailblazers.sentimentalanalysis.utils.ResponseCodeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final VerificationRepository verificationRepository;
    private final ResponseCodeUtil responseCodeUtil = new ResponseCodeUtil();
    private final ApplicationUtil applicationUtil;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final EmailService emailService;
    @Override
    public BaseResponseUtil signUp(SignupRequestDto signupRequest) {
        BaseResponseUtil response = new BaseResponseUtil();

        if (signupRequest.getFirstname().trim().length() == 0){
            return responseCodeUtil.updateResponseData(response, ResponseCodeEnum.ERROR, "Firstname cannot be empty.");
        }

        if (signupRequest.getLastname().trim().length() == 0){
            return responseCodeUtil.updateResponseData(response, ResponseCodeEnum.ERROR, "Lastname cannot be empty.");
        }

        if (!applicationUtil.validEmail(signupRequest.getEmail())){
            return responseCodeUtil.updateResponseData(response, ResponseCodeEnum.ERROR, "Invalid email address.");
        }

        if (signupRequest.getPassword().trim().length() == 0){
            return responseCodeUtil.updateResponseData(response, ResponseCodeEnum.ERROR, "Password cannot be empty");
        }

        Boolean isUserExist = userRepository.existsByEmail(signupRequest.getEmail());

        if (isUserExist){
            return responseCodeUtil.updateResponseData(response, ResponseCodeEnum.ERROR, "User already exist.");
        }

        User newUser = new User();
        newUser.setFirstname(signupRequest.getFirstname());
        newUser.setLastname(signupRequest.getLastname());
        newUser.setEmail(signupRequest.getEmail());
        newUser.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        newUser.setIsEmailVerified(false);
        newUser.setRole(Role.USER);
        userRepository.save(newUser);

        Verification verification = new Verification(newUser);
        verificationRepository.save(verification);

        var jwtToken = jwtService.generateToken(newUser);
        AuthenticationResponse authResponse = AuthenticationResponse.builder()
                .isEmailVerified(false)
                .token(jwtToken)
                .build();
        authResponse.setCode(0);
        authResponse.setDescription(ResponseCodeEnum.SUCCESS.getDescription());

        emailService.sendSimpleMailMessage(newUser.getFirstname(), newUser.getEmail(), verification.getToken());

        return responseCodeUtil.updateResponseDataReturnObject(new BaseResponseUtil(), ResponseCodeEnum.SUCCESS, authResponse);
    }

    @Override
    public Boolean verifyToken(String token) {
        Verification verification = verificationRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid Token"));
        User user = userRepository.findByEmailIgnoreCase(verification.getUser().getEmail())
                .orElseThrow(() -> new RuntimeException("Bad Credentials"));
        user.setIsEmailVerified(true);
        userRepository.save(user);
        return Boolean.TRUE;
    }

    @Override
    public BaseResponseUtil login(LoginRequestDto loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        User user = userRepository.findByEmailIgnoreCase(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("Bad Credentials"));

        var jwtToken = jwtService.generateToken(user);

        LoginResponseDto response = LoginResponseDto.builder()
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .token(jwtToken)
                .build();
        response.setCode(0);
        response.setDescription(ResponseCodeEnum.SUCCESS.getDescription());
        return responseCodeUtil.updateResponseDataReturnObject(new BaseResponseUtil(), ResponseCodeEnum.SUCCESS, response);
    }
}
