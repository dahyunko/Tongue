package com.example.demo.jwt;

import com.example.demo.model.users.UserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtManager {
    private final String securityKey = "hello world"; // TODO 민감정보는 따로 분리하는 것이 좋다
    private final Long expiredTime = 1000 * 60L * 60L * 3L; // 유효시간 3시간

    /**
     * Member 정보를 담은 JWT 토큰을 생성한다.
     *
     * @param member Member 정보를 담은 객체
     * @return String JWT 토큰
     */
    public String generateJwtToken(UserDto userDto) {
        Date now = new Date();
        return Jwts.builder()
                .setSubject(userDto.getUserId()) // 보통 username
                .setHeader(createHeader())
                .setClaims(createClaims(userDto)) // 클레임, 토큰에 포함될 정보
                .setExpiration(new Date(now.getTime() + expiredTime)) // 만료일
                .signWith(SignatureAlgorithm.HS256, securityKey)
                .compact();
    }

    /**
     * 클레임(Claim)을 생성한다.
     *
     * @param userDto 토큰을 생성하기 위한 계정 정보를 담은 객체
     * @return Map<String, Object> 클레임(Claim)
     */
    private Map<String, Object> createClaims(UserDto userDto) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userDto.getUserId()); // username
        claims.put("userName", userDto.getUserName()); // 인가정보
        return claims;
    }

    private Map<String, Object> createHeader() {
        Map<String, Object> header = new HashMap<>();
        header.put("typ", "JWT");
        header.put("alg", "HS256"); // 해시 256 사용하여 암호화
        header.put("regDate", System.currentTimeMillis());
        return header;
    }

    /**
     * Token 에서 Claim 을 가져온다.
     *
     * @param token JWT 토큰
     * @return Claims 클레임
     */
    private Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(securityKey).parseClaimsJws(token).getBody();
    }

    /**
     * 토큰으로 부터 username 을 가져온다.
     *
     * @param token JWT 토큰
     * @return String Member 의 username
     */
    public String getUsernameFromToken(String token) {
        return (String) getClaims(token).get("userId");
    }

}
