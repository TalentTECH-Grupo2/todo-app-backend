package com.talent_tech.todo_app_backend.repository;

import com.talent_tech.todo_app_backend.model.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITodoRepository extends JpaRepository<TodoEntity,Long> {
}
