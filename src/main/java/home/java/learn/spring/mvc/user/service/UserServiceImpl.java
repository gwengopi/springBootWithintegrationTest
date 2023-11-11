package home.java.learn.spring.mvc.user.service;

import home.java.learn.spring.mvc.user.UserRepository;
import home.java.learn.spring.mvc.user.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    // For simplicity, I'm using an in-memory list to store users.
/*    private final List<UserDTO> users;

    public UserServiceImpl(List<UserDTO> users) {
        this.users = users;
    }*/

    @Override
    public UserDTO registerUser(UserDTO user) {

        return user;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return users;
    }

    @Override
    public Optional<UserDTO> getUserById(Long userId) {
        return users.stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst();
    }

    @Override
    public void deleteUser(Long userId) {
        users.removeIf(user -> user.getId().equals(userId));
    }
}
