package com.promm.backboard.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import com.promm.backboard.security.MemberRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long mid;

    @Column(unique = true,length = 100)
    private String username;

    @Column(unique = true,length = 150)
    private String email;

    @Column
    private String password;

    @CreatedDate
    @Column(name ="regDate", updatable = false)
    private LocalDateTime regDate; //회원가입일

    @Enumerated(value = EnumType.STRING) //Enum타입이 String인 이유는 value가 "ROLE_ADMIN", "ROLE_USER" 이기 때문
    @Column(length = 15)
    private MemberRole role;
}
