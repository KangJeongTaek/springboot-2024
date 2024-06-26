package com.promm.backboard.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    @OneToMany(mappedBy = "board",cascade = CascadeType.REMOVE)
    private List<Replay> replayList;

    @LastModifiedDate
    @Column(name="modifyDate")
    private LocalDateTime modifyDate; //24.06.24 수정일 추가

    // @Column(columnDefinition = "integer default 0")
    // @ColumDefault("")
    @Column
    private Integer hit; // 24.06.26 조회수 추가

    //사용자가 여러개의 게시글을 작성할 수 있다 다대일 설정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "mid",name = "writer")
    private Member writer;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id",name="category_id")
    private Category category;
}
