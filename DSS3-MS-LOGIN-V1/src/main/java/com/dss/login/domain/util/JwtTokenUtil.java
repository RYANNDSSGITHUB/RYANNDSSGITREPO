package com.dss.login.domain.util;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    @Value("${jwt.token.validity}")
    private long TOKEN_VALIDITY;

    public Claims getClaims(final String token) {
        try {
            Claims body = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
            return body;
        } catch (Exception e) {
            System.out.println(e.getMessage() + " => " + e);
        }
        return null;
    }

    public String generateAccessToken(String id) {
        Claims claims = Jwts.claims().setSubject(id);
        long nowMillis = System.currentTimeMillis();
        long expMillis = nowMillis + TOKEN_VALIDITY;
        Date exp = new Date(expMillis);
        return Jwts.builder().setClaims(claims).setIssuedAt(new Date(nowMillis)).setExpiration(exp)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
    }

    public boolean validate(String token){
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
        } catch (SignatureException e) {
            throw new MalformedJwtException("Invalid JWT signature");
        } catch (MalformedJwtException e) {
            throw new MalformedJwtException("Invalid JWT token");
        } catch (ExpiredJwtException e) {
            throw new MalformedJwtException("Expired JWT token");
        } catch (UnsupportedJwtException e) {
            throw new MalformedJwtException("Unsupported JWT token");
        } catch (IllegalArgumentException e) {
            throw new MalformedJwtException("JWT claims string is empty.");
        }
        return true;
    }


}
