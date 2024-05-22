package com.example.demo.config;

import com.example.demo.jwt.JWTFilter;
import com.example.demo.jwt.JWTUtil;
import com.example.demo.jwt.LoginFilter;
import com.example.demo.model.users.UserRole;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    //AuthenticationManager가 인자로 받을 AuthenticationConfiguraion 객체 생성자 주입
    private final AuthenticationConfiguration authenticationConfiguration;

    //JWTUtil 주입
    private final JWTUtil jwtUtil;

    public SecurityConfig(AuthenticationConfiguration authenticationConfiguration, JWTUtil jwtUtil) {
        this.authenticationConfiguration = authenticationConfiguration;
        this.jwtUtil = jwtUtil;
    }

    // 계층 권한 메소드 등록
    @Bean
    public RoleHierarchy roleHierarchy(){
        RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();

        hierarchy.setHierarchy("ADMIN > USER");
        return hierarchy;
    }

    //AuthenticationManager Bean 등록
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOriginPattern("*"); // 모든 출처 허용
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L); // 3600초 동안 preflight 결과를 캐시에 저장
        configuration.setExposedHeaders(Collections.singletonList("Authorization"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        //csrf disable, rest api 사용할 경우 csrf 필요 없음
        http.csrf(AbstractHttpConfigurer::disable);

        //form 로그인 방식 disable
        http.formLogin(AbstractHttpConfigurer::disable);

        //http basic 인증 방식 disable
        http.httpBasic(AbstractHttpConfigurer::disable);

        // 경로별 인가 작업
        http.authorizeHttpRequests((auth)-> auth
                    .requestMatchers("/user/**", "/login", "/").permitAll()
                    .requestMatchers("/travel/**" , "/user/**", "/board/**", "/mypage/**", "/magazine/**", "/magazine/comment/**").hasAuthority(String.valueOf(UserRole.USER))
                    .requestMatchers("/travel/**").hasAnyRole(UserRole.USER.name(), UserRole.ADMIN.name())
                    .anyRequest().authenticated()
        );

        // JWTFilter 등록
        http.addFilterBefore(new JWTFilter(jwtUtil), LoginFilter.class);

        //AuthenticationManager()와 JWTUtil 인수 전달
        http.addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil), UsernamePasswordAuthenticationFilter.class);

//        // 다중 로그인 설정
//        http
//                .sessionManagement((auth) -> auth
//                    .maximumSessions(1)
//                    .maxSessionsPreventsLogin(true));
//
//        // 로그인 시 동일한 세션에 대한 id 변경
//        http
//                .sessionManagement((auth)-> auth
//                        .sessionFixation().changeSessionId());

        // 세션 STATELESS 설정, JWT를 통한 인증/ 인가를 위해
        http.sessionManagement((session)->session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.cors(cors -> cors.configurationSource(corsConfigurationSource()));
//        http.cors((corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {
//
//                    @Override
//                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
//
//                        CorsConfiguration configuration = new CorsConfiguration();
//
////                        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:5173"));
//                        configuration.addAllowedOriginPattern("*");
//                        configuration.addAllowedHeader("*");
//                        configuration.addAllowedMethod("*");
//                        configuration.setAllowCredentials(true);
//                        configuration.setMaxAge(3600L);
//
//                        configuration.setExposedHeaders(Collections.singletonList("Authorization"));
//
//                        return configuration;
//                    }
//                })));

        return http.build();
    }
}
