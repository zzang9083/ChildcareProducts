package euljiro.project.childcareproducts.common.config;

import euljiro.project.childcareproducts.common.config.jwt.JwtAccessDeniedHandler;
import euljiro.project.childcareproducts.common.config.jwt.JwtAuthFilter;
import euljiro.project.childcareproducts.common.config.jwt.JwtAuthenticationEntryPoint;
import euljiro.project.childcareproducts.common.config.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@Log4j2
public class WebSecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;



    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> {
            web.ignoring()                                          // filter 처리제외
                    .requestMatchers(
                            "/api/v1/user/login"
                                    , "/api/v1/user/refresh"
                            ,"/swagger-ui/**"
                    );
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        log.info("filterChain started");

        http.authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest()  // 권한에 대한 필터는 모두 허용
                        .permitAll())
                .csrf(AbstractHttpConfigurer::disable)

                .exceptionHandling((exceptionConfig) ->                             // 인증처리시 exception 핸들링
                        exceptionConfig.accessDeniedHandler(jwtAccessDeniedHandler).authenticationEntryPoint(jwtAuthenticationEntryPoint)
                )
                // jwt 인증 filter
                .addFilterBefore(new JwtAuthFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
