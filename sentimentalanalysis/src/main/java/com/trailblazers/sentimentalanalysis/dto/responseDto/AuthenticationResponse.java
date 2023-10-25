package com.trailblazers.sentimentalanalysis.dto.responseDto;

import com.trailblazers.sentimentalanalysis.utils.BaseResponseUtil;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse extends BaseResponseUtil {
    private Boolean isEmailVerified;
    private String token;
}
