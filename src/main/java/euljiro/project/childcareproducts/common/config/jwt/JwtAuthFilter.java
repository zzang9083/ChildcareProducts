package euljiro.project.childcareproducts.common.config.jwt;

import euljiro.project.childcareproducts.common.exception.BaseException;
import euljiro.project.childcareproducts.common.exception.JwtAuthenticationException;
import euljiro.project.childcareproducts.common.exception.JwtExcepion;
import euljiro.project.childcareproducts.common.response.ErrorCode;
import io.lettuce.core.RedisConnectionException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Log4j2
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        return path.startsWith("/api/v1/login"); // 여기에 예외 처리할 경로 추가
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        log.info("doFilterInternal started");


        try {
            // 클라이언트의 api 요청 헤더에서 토큰 추출
            String accessToken = jwtTokenProvider.resolveToken((HttpServletRequest) request);
            log.info("accessToken: "+ accessToken);

            if (accessToken == null) {
                throw new JwtAuthenticationException("jwt Authentication exception occurs!");
            }

            //토큰 검증
            if (accessToken != null && jwtTokenProvider.validateToken(accessToken)) {
                Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (RedisConnectionException e) {
            log.error("RedisConnectionException :"+ e.getMessage());
            SecurityContextHolder.clearContext();
            throw new BaseException(ErrorCode.REDIS_CONNECTION_FAILURE);
        }
          catch (JwtExcepion e) {
            log.error("JwtExcepion errorCode:"+e.getErrorCode());
            log.error("JwtExcepion mesage:"+e.getMessage());
            throw e;
        } catch (JwtAuthenticationException e) {
            log.error("JwtAuthentication Authentication Exception Occurs! - {}",e.getClass());
        }
        // 다음 필터링
        filterChain.doFilter(request, response);
    }

}
