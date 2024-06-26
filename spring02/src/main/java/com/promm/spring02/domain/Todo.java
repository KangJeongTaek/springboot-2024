package com.promm.spring02.domain;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Todo {
     private int tno;

     private String title;

     private LocalDateTime dueDate;

     private String writer;

     private int isDone;
}
