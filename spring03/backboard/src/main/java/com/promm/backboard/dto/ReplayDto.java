package com.promm.backboard.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplayDto {
    
    private Long rno;

    private String content;
    
    private LocalDateTime createDate;

    private LocalDateTime modifyDate;

    private String writer;
}
