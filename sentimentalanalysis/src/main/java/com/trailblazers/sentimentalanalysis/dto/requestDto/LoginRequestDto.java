package com.trailblazers.sentimentalanalysis.dto.requestDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {

    @Email(message = "Provide a valid email address")
    private String email;
    @NotBlank(message = "Password cannot be blank")
    private String password;
}
