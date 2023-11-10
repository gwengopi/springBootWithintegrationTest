package home.java.learn.spring.mvc.user.mapper;

import home.java.learn.spring.mvc.user.dto.UserDTO;
import home.java.learn.spring.mvc.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper{

    UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    UserDTO toUserDto(User user);

    User toUser(UserDTO user);

}
