package com.trailblazers.sentimentalanalysis.utils;

import com.trailblazers.sentimentalanalysis.enums.ResponseCodeEnum;
import lombok.Data;

@Data
public class BaseResponseUtil {
    private int code;
    private String description;

    public BaseResponseUtil(){
        this(ResponseCodeEnum.ERROR);
    }

    public BaseResponseUtil(ResponseCodeEnum responseCode){
        this.code = responseCode.getCode();
        this.description = responseCode.getDescription();
    }

    public void assignResponseCodeAndDescription(int code, String description){
        this.code = code;
        this.description = description;
    }

    public BaseResponseUtil(ResponseCodeEnum responseCode, String description){
        this.code = responseCode.getCode();
        this.description = description;
    }
}
