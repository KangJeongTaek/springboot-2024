package com.promm.spring02.service;

import java.util.List;

import com.promm.spring02.domain.Todo;

public interface TodoService {

    public List<Todo> getTodoAll() throws Exception;

    public Todo getTodoByTno(int tno) throws Exception;
}
