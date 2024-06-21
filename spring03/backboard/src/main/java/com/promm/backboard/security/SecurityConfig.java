package com.promm.backboard.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// 스프링 시큐리티 학심 파일
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true) //@PreAuthorize 사용설정
public class SecurityConfig {
    
    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        // http://localhost:8080/**  로그인하지 않고도 접근할 수 있는 권한을 줌
        // 개시판 보기 가능
        // (atr) -> atr.requestMatchers(new AntPathRequestMatcher("/**"))

        
        httpSecurity.authorizeHttpRequests((atr) -> atr.requestMatchers(new AntPathRequestMatcher("/**"))
        // 로그인, 회원 가입 페이지만 접근 가능하게 설정
        // (atr) -> atr.requestMatchers(new AntPathRequestMatcher("/member/register"),new AntPathRequestMatcher("/member/login"))
                                            .permitAll())
                                            // CSRF 위번조 공격을 막는 설정을 해제
                                            .csrf(csrf -> csrf.ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**")))
                                            //h2-console 페이지가 frameset, frame으로 구성 CORS와 유사한 옵션추가
                                            .headers(headers -> headers.addHeaderWriter(new XFrameOptionsHeaderWriter(
                                                XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN // ignoringRequestMatchers 영역에 있는 프레임이니 해제 요청
                                            )))
                                            .formLogin((fl) -> fl.loginPage("/member/login").defaultSuccessUrl("/"))
                                            // 로그인 url을 지정

                                            .logout((logout) -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout")).logoutSuccessUrl("/")
                                            .invalidateHttpSession(true))
                                            ;

        
        return httpSecurity.build();
    }
    
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(); // 암호화를 빈으로 생성
    }
}
