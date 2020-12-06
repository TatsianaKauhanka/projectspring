package by.itech.projectspring.service;

import by.itech.projectspring.entity.*;
import by.itech.projectspring.repository.UserRepository;
import by.itech.projectspring.utils.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;


import java.util.UUID;

@Service("userService")
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserServiceI<User> {
    private final UserRepository userRepository;
    private final CarService carService;
    private final DriverLicenseService licenseService;

    public User get(UUID uuid) {
        return userRepository.findByUuid(uuid)
                .orElseThrow(UserNotFoundException::new);
    }

    public void delete(UUID uuid) {
        userRepository.deleteByUuid(uuid);
    }

    public User save(User user) {
        log.debug("Saving user {}", user);
        user.setUuid(UUID.randomUUID());
        User userFromDB = userRepository.save(user);
        log.debug("User {} has been saved", userFromDB);
        return userFromDB;
    }

    public void update(User user) {
        log.debug("Updating user {}", user);
        if(user instanceof Owner){
            Owner userFromDB =(Owner) get(user.getUuid());
            userFromDB.setCars(((Owner) user).getCars());
        }
        else if(user instanceof Renter){
            Renter userFromDB = (Renter)get(user.getUuid());
            userFromDB.setRentDays(((Renter)user).getRentDays());
        }
        User userFromDB =get(user.getUuid());
        updateName(user, userFromDB);
        userRepository.save(userFromDB);
        log.debug("User {} has been updated", user);
    }

    private void updateName(User user, User userDB) {
        log.debug("Updating user{} name", user);
        if (!user.getFirstName().isEmpty())
            userDB.setFirstName(user.getFirstName());
        if (!user.getLastName().isEmpty())
            userDB.setLastName(user.getLastName());
        log.debug("User {} has been updated", user);
    }

    public void addCar(UUID userId, UUID vinNumber) {
        log.debug("Adding car{} to user{}", vinNumber, userId);
        User userFromDB = get(userId);
        Car carFromDB = carService.get(vinNumber);
        userFromDB.getCarList().add(carFromDB);
        userRepository.save(userFromDB);
        log.debug("Car{} has been added to user{}", vinNumber, userId);
    }

    public void deleteCar(UUID userId, UUID vinNumber) {
        log.debug("Deleting car{} from user{}", vinNumber, userId);
        User userFromDB = get(userId);
        Car carFromDB = carService.get(vinNumber);
        userFromDB.getCarList().remove(carFromDB);
        userRepository.save(userFromDB);
        log.debug("Car {} has been deleted from user{}", vinNumber, userId);
    }

    public void addLicense(UUID userId, UUID licenseUuid){
        log.debug("Adding driver license {} to user {}", licenseUuid, userId);
        User userFromDB = get(userId);
        DriverLicense licenseFromDB = licenseService.get(licenseUuid);
        userFromDB.setLicense(licenseFromDB);
        userRepository.save(userFromDB);
        log.debug("Driver license {} has been added to user{}", licenseUuid, userId);
    }

    public void deleteLicense(UUID userId, UUID licenseUuid){
        log.debug("Deleting driver license {} to user {}", licenseUuid, userId);
        User userFromDB = get(userId);
        userFromDB.setLicense(null);
        userRepository.save(userFromDB);
        log.debug("Driver license {} has been deleted to user{}", licenseUuid, userId);

    }
}
