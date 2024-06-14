package com.promm.spring02.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.promm.spring02.domain.Todo;
import com.promm.spring02.mapper.TodoMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TodoServiceImpl implements TodoService{

    private final TodoMapper todoMapper;

    @Override
    public List<Todo> getTodoAll() throws Exception {
        return todoMapper.selectTodoAll();
    }

    @Override
    public Todo getTodoByTno(int tno) throws Exception {
        return todoMapper.selectTodoByTno(tno);
    }
}
