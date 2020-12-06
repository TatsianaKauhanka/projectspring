package by.itech.projectspring.utils.mapper;

import by.itech.projectspring.dto.UserDTO;
import by.itech.projectspring.entity.User;
import org.mapstruct.Mapper;


@Mapper(componentModel="spring")
public interface UserMapper {
    UserDTO userEntityToUserDTO(User user);
    User userDTOtoUserEntity(UserDTO userDTO);
}
