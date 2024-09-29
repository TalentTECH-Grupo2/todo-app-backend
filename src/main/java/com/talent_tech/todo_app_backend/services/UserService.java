package com.talent_tech.todo_app_backend.services;

import com.talent_tech.todo_app_backend.model.UserEntity;
import com.talent_tech.todo_app_backend.repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final IUserRepository iUserRepository;

    public UserService(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    public List<UserEntity> getAllUsers() {
        return iUserRepository.findAll();
    }

    public UserEntity createUser(UserEntity userEntity){
        return iUserRepository.save(userEntity);
    }

}
