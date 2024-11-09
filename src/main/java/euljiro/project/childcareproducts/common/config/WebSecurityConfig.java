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
        return web -> web.ignoring()
                .requestMatchers("/api/v1/login", "/api/v1/login/**"); // 필터에서 제외할 URL

    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(authorizeRequests -> authorizeRequests.anyRequest()  // 권한에 대한 필터는 모두 허용
                        .permitAll())
                .csrf(AbstractHttpConfigurer::disable)

                .exceptionHandling(exceptionConfig ->
                        exceptionConfig.accessDeniedHandler(jwtAccessDeniedHandler)
                                .authenticationEntryPoint(jwtAuthenticationEntryPoint)) // 예외 처리

                // jwt 인증 filter
                .addFilterBefore(new JwtAuthFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
                .formLogin().disable(); // 기본 폼 로그인 필터 비활성화

        return http.build();
    }

}
