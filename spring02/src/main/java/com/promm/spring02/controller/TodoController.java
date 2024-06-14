package com.promm.spring02.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.promm.spring02.domain.Todo;
import com.promm.spring02.service.TodoService;

import jakarta.annotation.Resource;



@Controller
public class TodoController {
    
    @Resource
    TodoService todoService;

    @GetMapping("/todos")
    public String getTodos(Model model) throws Exception {
        List<Todo> todos = todoService.getTodoAll();
        model.addAttribute("todoList",todos);
        return "todoList";
    }

    // http://localhost:8091/todo/1
    @GetMapping("/todo/{tno}")
    public Todo getTodo(@PathVariable("tno") int tno) throws Exception {
        return todoService.getTodoByTno(tno);
    }
}
