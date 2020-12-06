package by.itech.projectspring.utils.mapper;


import by.itech.projectspring.dto.UserDTO;
import by.itech.projectspring.entity.Owner;
import by.itech.projectspring.entity.Renter;
import by.itech.projectspring.entity.SportCar;
import by.itech.projectspring.entity.User;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;


@Component
@Primary
public class UserMapperImp implements UserMapper {
    @Override
    public UserDTO userEntityToUserDTO(User user) {
        if ( user == null ) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setUserType("unspecified");
        if(user instanceof Owner){
            userDTO.setCars(((Owner) user).getCars());
            userDTO.setUserType("owner");
        }
        if(user instanceof Renter){
            userDTO.setRentDays(((Renter) user).getRentDays());
            userDTO.setUserType("renter");
        }

        userDTO.setUuid( user.getUuid() );
        userDTO.setLastName( user.getLastName() );
        userDTO.setFirstName( user.getFirstName() );

        return userDTO;
    }

    @Override
    public User userDTOtoUserEntity(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }
        User user = createUser(userDTO);
        user.setUuid( userDTO.getUuid() );
        user.setLastName( userDTO.getLastName() );
        user.setFirstName( userDTO.getFirstName() );

        return user;
    }
    private User createUser(UserDTO userDTO){
        if (userDTO.getUserType().equals("owner")){
           Owner user = new Owner();
           user.setCars(userDTO.getCars());
           return user;
        }
        else if(userDTO.getUserType().equals("renter")){
            Renter user = new Renter();
            user.setRentDays(userDTO.getRentDays());
            return user;
        }
        else return new User();
    }
}
