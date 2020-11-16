package by.itech.projectspring.service;

import by.itech.projectspring.bean.Car;
import by.itech.projectspring.bean.User;
import by.itech.projectspring.persistence.UserRepository;
import by.itech.projectspring.utils.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.UUID;

@Service("userService")
@RequiredArgsConstructor
public class UserService implements UserServiceInterface<User> {
    private final UserRepository userRepository;
    private final CarService carService;
    
    public User get(UUID uuid) {
        return userRepository.findByUuid(uuid)
                .orElseThrow(UserNotFoundException::new);
    }
    
    @Transactional
    public void delete(UUID uuid) {
        userRepository.deleteByUuid(uuid);
    }
    
    public User save(User user) {
        user.setUuid(UUID.randomUUID());
        return userRepository.save(user);
    }


    public void update(User user) {
        User userFromDB = get(user.getUuid());
        updateName(user, userFromDB);
        userRepository.save(userFromDB);
    }

    private void updateName(User user, User userDB) {
        if (!user.getFirstName().isEmpty())
            userDB.setFirstName(user.getFirstName());
        if (!user.getLastName().isEmpty())
            userDB.setLastName(user.getLastName());
    }
    
    public void addCar(User user) {
        User userFromDB = get(user.getUuid());
        for (Car c : user.getCarList())
            userFromDB.getCarList().add(carService.get(c.getVinNumber()));
        userRepository.save(userFromDB);
    }

    public void deleteCar(User user) {
        User userFromDB = get(user.getUuid());

        for (Car c : user.getCarList())
            userFromDB.getCarList().remove(c);

        userRepository.save(userFromDB);
    }
}
