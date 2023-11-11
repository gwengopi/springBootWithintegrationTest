package home.java.learn.spring.mvc.user.service;

import home.java.learn.spring.mvc.user.UserRepository;
import home.java.learn.spring.mvc.user.dto.UserDTO;
import home.java.learn.spring.mvc.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;
    private final List<UserDTO> users;

    public UserServiceImpl(List<UserDTO> users) {
        this.users = users;
    }

    @Override
    public UserDTO registerUser(UserDTO user) {

        return user;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return null;
    }

    @Override
    public Optional<UserDTO> getUserById(Long userId) {
//        return users.stream().map(userMapper::toUser).collect(Collectors.toList()).s
//                .filter(user -> user.getId().equals(userId))
//                .findFirst();
        return Optional.empty();
    }

    @Override
    public void deleteUser(Long userId) {
//        users.removeIf(user -> user.getId().equals(userId));

    }
}
