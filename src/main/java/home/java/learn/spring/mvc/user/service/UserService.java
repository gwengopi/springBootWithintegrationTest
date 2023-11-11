package home.java.learn.spring.mvc.user.service;

import home.java.learn.spring.mvc.user.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDTO registerUser(UserDTO user);

    List<UserDTO> getAllUsers();

    Optional<UserDTO> getUserById(Long userId);

    void deleteUser(Long userId);
}
