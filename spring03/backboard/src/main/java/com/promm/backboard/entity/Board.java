package com.promm.backboard.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//게시판 보드 테이블 엔터티
@Entity // 테이블화
@Getter
@Setter
@Builder // 객체 생성을 간략화
@NoArgsConstructor // 디폴트 생성자 자동 생성
@AllArgsConstructor // 멤버변수 모두를 파라미터로 가지는 생성자 자동생성
public class Board {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long bno; // PK

    @Column(name="title", length=250)
    private String title; // 글제목

    @Column(name="content", length=4000)
    private String content; // 글내용

    @Column(name = "createDate",updatable=false)
    @CreatedDate
    private LocalDateTime createDate; // 글 생성 시간

    //중요 (양방향 매핑이 필요한가?)
    // @OneToMany(mappedBy = "board",cascade = CascadeType.REMOVE)
    // private List<Replay> replyList;
}
