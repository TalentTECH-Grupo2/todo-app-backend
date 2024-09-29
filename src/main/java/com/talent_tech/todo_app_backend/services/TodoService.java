package com.talent_tech.todo_app_backend.services;

import com.talent_tech.todo_app_backend.exceptions.ResourceNotFoundException;
import com.talent_tech.todo_app_backend.model.TodoEntity;
import com.talent_tech.todo_app_backend.model.UserEntity;
import com.talent_tech.todo_app_backend.repository.ITodoRepository;
import com.talent_tech.todo_app_backend.repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    private final ITodoRepository iTodoRepository;

    private final IUserRepository iUserRepository;

    public TodoService(ITodoRepository iTodoRepository, IUserRepository iUserRepository) {
        this.iTodoRepository = iTodoRepository;
        this.iUserRepository = iUserRepository;
    }
    public List<TodoEntity> getAllTodos(){
        return iTodoRepository.findAll();
    }

    public TodoEntity createTodo(TodoEntity todoEntity){
        todoEntity.setTodoIsCompleted(false);
        return iTodoRepository.save(todoEntity);
    }

    public void deleteTodo(Long id) throws ResourceNotFoundException {
        Optional<TodoEntity> todoOptional = iTodoRepository.findById(id);
        if (todoOptional.isPresent()) {
            iTodoRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Tarea con ID " + id + " no encontrada");
        }
    }

    public TodoEntity assignUserForTodo(Long todoId, Long userId) throws ResourceNotFoundException{
        Optional<UserEntity> optionalUser = iUserRepository.findById(userId);
        if(optionalUser.isEmpty()){
            throw new ResourceNotFoundException("No hay ningún usuario asociado al ID: "  + userId);
        }
        Optional<TodoEntity> optionalTodo = iTodoRepository.findById(todoId);
        if(optionalTodo.isEmpty()){
            throw new ResourceNotFoundException("No hay ninguna tarea asociada al ID: "  + todoId);
        }
        optionalTodo.get().setUser(optionalUser.get());
        TodoEntity modifiedTodo = optionalTodo.get();
        iTodoRepository.save(modifiedTodo);
        return  modifiedTodo;
    }

    public TodoEntity toggleTodoIsCompleted(Long todoId) throws ResourceNotFoundException {
        Optional<TodoEntity> optionalTodo = iTodoRepository.findById(todoId);
        if(optionalTodo.isEmpty()){
            throw new ResourceNotFoundException("No hay ninguna tarea asociada al ID: "  + todoId);
        }
        optionalTodo.get().setTodoIsCompleted(!optionalTodo.get().getTodoIsCompleted());
        iTodoRepository.save(optionalTodo.get());
        return optionalTodo.get();
    }

}
