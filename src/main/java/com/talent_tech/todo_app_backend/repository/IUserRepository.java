package com.talent_tech.todo_app_backend.repository;

import com.talent_tech.todo_app_backend.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity,Long> {

}
