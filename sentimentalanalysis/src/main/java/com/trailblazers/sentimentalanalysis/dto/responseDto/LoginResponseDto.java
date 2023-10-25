package com.trailblazers.sentimentalanalysis.dto.responseDto;

import com.trailblazers.sentimentalanalysis.utils.BaseResponseUtil;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto extends BaseResponseUtil {
    private String firstname;
    private String lastname;
    private String email;
    private String token;
}
