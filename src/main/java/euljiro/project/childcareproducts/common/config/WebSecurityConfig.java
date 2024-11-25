package euljiro.project.childcareproducts.common.config;

import euljiro.project.childcareproducts.common.config.jwt.JwtAccessDeniedHandler;
import euljiro.project.childcareproducts.common.config.jwt.JwtAuthFilter;
import euljiro.project.childcareproducts.common.config.jwt.JwtAuthenticationEntryPoint;
import euljiro.project.childcareproducts.common.config.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@RequiredArgsConstructor
@EnableWebMvc
@EnableWebSecurity
public class WebSecurityConfig implements WebMvcConfigurer {

    private final JwtTokenProvider jwtTokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;





    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring()
                //.requestMatchers("/api/v1/login", "/api/v1/login/**"); // 필터에서 제외할 URL
                .requestMatchers("/api/**"); // 필터에서 제외할 URL

    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(authorizeRequests -> authorizeRequests.anyRequest()  // 권한에 대한 필터는 모두 허용
                        .permitAll())
                .csrf(AbstractHttpConfigurer::disable)

                .exceptionHandling(exceptionConfig ->
                        exceptionConfig.accessDeniedHandler(jwtAccessDeniedHandler)
                                .authenticationEntryPoint(jwtAuthenticationEntryPoint)) // 예외 처리

                // jwt 인증 filter
                .addFilterBefore(new JwtAuthFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);

        return http.build();

//        return http
//                .authorizeHttpRequests(authorizeRequests ->
//                        authorizeRequests
//                                .requestMatchers("/api/v1/login/**").permitAll()  // 로그인, 회원가입 등은 허용
//                                .anyRequest().authenticated()  // 나머지 요청은 인증 필요
//                )
//                .addFilterBefore(new JwtAuthFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class) // JWT 필터 추가
//                .build();
//        return http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/api/auth/**").permitAll()  // 로그인, 회원가입 등은 허용
//                .anyRequest().authenticated()  // 나머지 요청은 인증 필요
//                .and()
//                .addFilterBefore(new JwtFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class) // JWT 필터 추가
//                .build();
//
//
//        http.authorizeHttpRequests(authorizeRequests -> authorizeRequests.anyRequest()  // 권한에 대한 필터는 모두 허용
//                        .permitAll())
//                .csrf(AbstractHttpConfigurer::disable)
//
//                .exceptionHandling(exceptionConfig ->
//                        exceptionConfig.accessDeniedHandler(jwtAccessDeniedHandler)
//                                .authenticationEntryPoint(jwtAuthenticationEntryPoint)) // 예외 처리
//
//                // jwt 인증 filter
//                .addFilterBefore(new JwtAuthFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class).build();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        // WebJars 제거
        // registry.addResourceHandler("/webjars/**")
        //         .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080")
                .allowedMethods(
                        HttpMethod.GET.name(),
                        HttpMethod.HEAD.name(),
                        HttpMethod.POST.name(),
                        HttpMethod.PUT.name(),
                        HttpMethod.DELETE.name());
    }

}
