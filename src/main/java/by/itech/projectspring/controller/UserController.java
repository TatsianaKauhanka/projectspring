package by.itech.projectspring.controller;

import by.itech.projectspring.dto.UserDTO;
import by.itech.projectspring.entity.User;
import by.itech.projectspring.service.UserServiceI;
import by.itech.projectspring.utils.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController; 

import java.util.UUID;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserServiceI<User> userService;
    private final UserMapper userMapper;


    @DeleteMapping(value = "/{uuid}")
    public void doDelete(@PathVariable("uuid") UUID uuid) {
        log.debug("Deleting user with {}", uuid);
        userService.delete(uuid);
        log.debug("User {} has been deleted", uuid);
    }

    @PostMapping(value = "/{subtype}")
    protected UserDTO doPost(@RequestBody UserDTO userDTO, @PathVariable String subtype) {
        log.debug("Saving user {} to DB", userDTO);
        userDTO.setUserType(subtype);
        User userFromDTO = userMapper.userDTOtoUserEntity(userDTO);
        User userFromService = userService.save(userFromDTO);
        UserDTO userDTOFromDB = userMapper.userEntityToUserDTO(userFromService);
        log.debug("User {} has been save to DB", userDTOFromDB);
        return userDTOFromDB;
    }

    @GetMapping(value = "/{uuid}")
    public UserDTO doGet(@PathVariable("uuid") UUID uuid) {
        log.debug("Finding user with uuid {}", uuid);
        User user = userService.get(uuid);
        log.debug("User {} has been found in DB", user);
        return userMapper.userEntityToUserDTO(user);
    }

    @PutMapping(value = "/{subtype}/{uuid}")
    protected void doPut(@RequestBody UserDTO userDTO, @PathVariable String subtype, @PathVariable UUID uuid) {
        log.debug("Updating user {}", userDTO);
        userDTO.setUuid(uuid);
        userDTO.setUserType(subtype);
        User user = userMapper.userDTOtoUserEntity(userDTO);
        userService.update(user);
        log.debug("User {} has been updated", user);
    }

    @PutMapping(value = "/{uuid}/addCar/{vin}")
       protected void addCar(@PathVariable UUID uuid, @PathVariable UUID vin){
        log.debug("Adding car with vinNumber {} to user {}", vin, uuid);
        userService.addCar(uuid, vin);
        log.debug("Car with vinNumber {} has been added to user with uuid {}", vin, uuid);
    }

    @PutMapping(value = "{uuid}/deleteCar/{vin}")
    protected void deleteCar(@PathVariable UUID uuid, @PathVariable UUID vin) {
        log.debug("Deleting car with vinNumber {} from user {}", vin, uuid);
        userService.deleteCar(uuid, vin);
        log.debug("Car with vinNumber {} has been deleted from user with uuid {}", vin, uuid);
    }

    @PutMapping(value = "/{uuid}/addLicense/{licenseUuid}")
    protected void addLicense(@PathVariable UUID uuid, @PathVariable UUID licenseUuid){
        log.debug("Adding driver license {} to user {}", licenseUuid, uuid);
        userService.addLicense(uuid, licenseUuid);
        log.debug("Driver license {} has been added to user {}", licenseUuid, uuid);
    }

    @PutMapping(value = "/{uuid}/deleteLicense/{licenseUuid}")
    protected void deleteLicense(@PathVariable UUID uuid, @PathVariable UUID licenseUuid){
        log.debug("Deleting driver license {} to user {}", licenseUuid, uuid);
        userService.deleteLicense(uuid, licenseUuid);
        log.debug("Driver license {} has been deleted to user {}", licenseUuid, uuid);
    }

}