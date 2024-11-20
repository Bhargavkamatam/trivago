package com.trivago.payload;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JWTTokenDto {
    private String type;

    public String getToken() {
        return Token;
    }

    public String getType() {
        return type;
    }

    private String Token;

    public void setType(String type) {
        this.type = type;
    }

    public void setToken(String token) {
        this.Token = token;
    }
}