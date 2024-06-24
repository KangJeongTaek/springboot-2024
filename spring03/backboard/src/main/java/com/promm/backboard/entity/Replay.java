package com.promm.backboard.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Replay {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long rno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "bno", name="bno")
    private Board board;

    @Column(length = 1000)
    private String content;

    @Column(name = "createDate",updatable = false)
    @CreatedDate
    private LocalDateTime createDate;

    @LastModifiedDate
    @Column(name="modifyDate")
    private LocalDateTime modifyDate; //24.06.24 수정일 추가

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "mid",name="writer")
    private Member writer;
}
