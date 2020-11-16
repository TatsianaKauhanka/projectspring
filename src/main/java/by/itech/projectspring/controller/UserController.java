package by.itech.projectspring.controller;

import by.itech.projectspring.bean.User;
import by.itech.projectspring.service.UserServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceInterface<User> userService;

    @DeleteMapping(value = "/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public void doDelete(@PathVariable("uuid") UUID uuid) {
        userService.delete(uuid);
    }

    @PostMapping
    protected User doPost(@RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping(value = "/{uuid}")
    public  User doGet(@PathVariable("uuid") UUID uuid){
        return userService.get(uuid);
    }

    @PutMapping
    protected void doPut(@RequestBody User user){
      userService.update(user);
    }

    @PutMapping(value = "/addCar")
    protected void addCar(@RequestBody User user){
        userService.addCar(user);
    }

    @PutMapping(value ="/deleteCar")
    protected void deleteCar(@RequestBody User user){
        userService.deleteCar(user);
    }

}
