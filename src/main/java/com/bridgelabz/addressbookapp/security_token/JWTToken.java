package com.bridgelabz.addressbookapp.security_token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

@Component
public class JWTToken {
    //We don't need change Secret key that's why we are using final key.
    private final String SECRET = "Sourabh";

    //Method for generating a token.
    public String createToken(int person_id){
        String token;
        token = JWT.create()
                .withClaim("id",person_id)//withClaim => Generating a token for a primary_key.
                .sign(Algorithm.HMAC256(SECRET));//sign => Signing a algorithm for security.
        return token;
    }

    public int decodeToken(String token) {
        int id =0;
        if (token!=null){
            id =JWT.require(Algorithm.HMAC256(SECRET))
                    .build().verify(token)
                    .getClaim("id").asInt();
        }
        return id;
    }
}
