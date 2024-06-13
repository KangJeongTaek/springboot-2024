package com.promm.spring02.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.promm.spring02.domain.Todo;

@Mapper
public interface TodoMapper {
    
    public List<Todo> selectTodoAll();

    public Todo selectTodo(Integer tno);
}
