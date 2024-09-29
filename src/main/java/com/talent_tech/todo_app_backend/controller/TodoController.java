package com.talent_tech.todo_app_backend.controller;

import com.talent_tech.todo_app_backend.controller.dto.AssignTodoDTO;
import com.talent_tech.todo_app_backend.exceptions.ResourceNotFoundException;
import com.talent_tech.todo_app_backend.model.TodoEntity;
import com.talent_tech.todo_app_backend.services.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public List<TodoEntity> getAllTodos(){
        return todoService.getAllTodos();
    }

    @PostMapping
    public TodoEntity createTodo(@RequestBody TodoEntity todoEntity){
        return todoService.createTodo(todoEntity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTodo(@PathVariable Long id) {
        try {
            todoService.deleteTodo(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @PutMapping
    public ResponseEntity assignUserForTodo (@RequestBody AssignTodoDTO assignTodoDTO){
        try{
            return ResponseEntity.ok(todoService.assignUserForTodo(assignTodoDTO.getTodoId(),assignTodoDTO.getUserId()));
        } catch (ResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity toggleTodoIsCompleted(@PathVariable("id") Long todoId){
        try{
            return ResponseEntity.ok(todoService.toggleTodoIsCompleted(todoId));
        } catch (ResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
