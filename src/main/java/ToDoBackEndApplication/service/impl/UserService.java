package ToDoBackEndApplication.service.impl;

import ToDoBackEndApplication.exception.ResourceNotFoundException;
import ToDoBackEndApplication.exception.InvalidDataException;
import ToDoBackEndApplication.exception.OperationFailedException;
import ToDoBackEndApplication.model.User;
import ToDoBackEndApplication.repository.IUserRepository;
import ToDoBackEndApplication.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron usuarios.");
        }
        return users;
    }

    @Override
    public User createUser(User user) {
        if (user == null || user.getFirstname() == null || user.getFirstname().isEmpty()) {
            throw new InvalidDataException("El usuario no puede estar vacío y debe tener un nombre válido.");
        }

        try {
            return userRepository.save(user);
        } catch (Exception e) {
            log.error("Error al crear el usuario: {}", e.getMessage());
            throw new OperationFailedException("Error al crear el usuario.");
        }
    }

    @Override
    public ResponseEntity<User> getUserById(String id) {
        return userRepository.findById(id)
                .map(user -> ResponseEntity.ok().body(user))
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id));
    }

    @Override
    public ResponseEntity<User> updateUser(String id, User user) {
        if (user == null || user.getFirstname() == null || user.getFirstname().isEmpty()) {
            throw new InvalidDataException("El usuario no puede estar vacío y debe tener un nombre válido.");
        }

        return userRepository.findById(id)
                .map(userData -> {
                    userData.setFirstname(user.getFirstname());  // Actualiza el nombre
                    userData.setLastname(user.getLastname());    // Actualiza el apellido
                    userData.setEmail(user.getEmail());          // Actualiza el email
                    userData.setRole(user.getRole());            // Actualiza el rol

                    try {
                        User updatedUser = userRepository.save(userData);
                        return ResponseEntity.ok().body(updatedUser);
                    } catch (Exception e) {
                        log.error("Error al actualizar el usuario: {}", e.getMessage());
                        throw new OperationFailedException("Error al actualizar el usuario.");
                    }
                }).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id));
    }

    @Override
    public ResponseEntity<?> deleteUser(String id) {
        return userRepository.findById(id)
                .map(user -> {
                    try {
                        userRepository.deleteById(id);
                        return ResponseEntity.ok().build();
                    } catch (Exception e) {
                        log.error("Error al eliminar el usuario: {}", e.getMessage());
                        throw new OperationFailedException("Error al eliminar el usuario.");
                    }
                }).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id));
    }
}
