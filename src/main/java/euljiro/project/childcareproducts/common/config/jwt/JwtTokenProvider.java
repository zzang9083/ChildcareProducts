package euljiro.project.childcareproducts.common.config.jwt;

import euljiro.project.childcareproducts.common.exception.JwtExcepion;
import euljiro.project.childcareproducts.common.response.ErrorCode;
import io.jsonwebtoken.*;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Base64;
import java.util.Date;

@RequiredArgsConstructor
@Component
@Log4j2
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    @Autowired
    private UserDetailsService userDetailsService;

    //객체 초기화, secretKey를 Base64로 인코딩
    @PostConstruct
    protected void init() {

        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        log.info("jwtSecret: "+ secretKey);
    }

    // 토큰 생성
    public String createToken(String userId) {
        Claims claims = Jwts.claims().setSubject(userId); // JWT payload에 저장되는 정보단위
        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(now.getTime() + (30 * 60 * 1000L))) // 토큰 유효시간(30분)
                .signWith(SignatureAlgorithm.HS256, secretKey)  // 암호화 알고리즘과 secret키
                .compact();
    }

    // 리프레시토큰 생성
    public String createRefreshToken(String userId) {
        Claims claims = Jwts.claims().setSubject(userId); // JWT payload에 저장되는 정보단위
        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                //(60 * 60 * 1000L * 3)))
                .setExpiration(new Date(now.getTime() + (60 * 60 * 1000L * 3))) // 토큰 유효시간(3시간)
                .signWith(SignatureAlgorithm.HS256, secretKey)  // 암호화 알고리즘과 secret키
                .compact();
    }

    // 인증 정보 조회
    public Authentication getAuthentication(String accessToken) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(getUserIdByToken(accessToken));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // 토큰으로 유저id 추출
    public String getUserIdByToken(String token) {
        return Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token).getBody().getSubject();
    }

    // 토큰 유효성, 만료일자 확인
    public boolean validateToken(String accessToken) {

        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(accessToken);
            return true;
        } catch (ExpiredJwtException e) {
            log.error("ExpiredJwtException :"+ e.getMessage());
            throw new JwtExcepion(ErrorCode.EXPIRED_TOKEN, ErrorCode.EXPIRED_TOKEN.getErrorMsg());
        } catch (JwtException e) {
            log.error("JwtExcepion :"+ e.getMessage());
            throw new JwtExcepion(ErrorCode.INVALID_ACCESS_TOKEN, ErrorCode.INVALID_ACCESS_TOKEN.getErrorMsg());
        } catch (Exception e) {
            log.error("Excepion :"+ e.getMessage());
            throw e;
        }
    }

    // Request Header에서 token 값 가져오기
    public String resolveToken(HttpServletRequest request) {

        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }


}
