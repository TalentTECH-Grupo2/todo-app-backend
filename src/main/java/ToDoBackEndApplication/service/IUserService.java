package ToDoBackEndApplication.service;

import ToDoBackEndApplication.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserService {



    List<User> getAllUsers();

    User createUser(User user);

    ResponseEntity<User> getUserById(String id);

    ResponseEntity<User> updateUser(String id, User user);

    ResponseEntity<?> deleteUser(String id);

}
