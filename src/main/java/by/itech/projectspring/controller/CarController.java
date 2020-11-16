package by.itech.projectspring.controller;


import by.itech.projectspring.bean.Car;
import by.itech.projectspring.service.CarServiceInterface;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/car")
@RequiredArgsConstructor
public class CarController {

    private final CarServiceInterface<Car> carService;

    @PostMapping
    protected @ResponseBody Car doPost(@RequestBody Car car) {
        return carService.save(car);
    }

    @GetMapping(value = "/{vinNumber}")
    public  Car doGet(@PathVariable ("vinNumber") UUID vinNumber){
        return carService.get(vinNumber);
    }

    @PutMapping
    protected @ResponseBody void doPut(@RequestBody Car car){
       carService.update(car);
    }

    @DeleteMapping(value = "/{vinNumber}")
    public  void doDelete(@PathVariable ("vinNumber") UUID vinNumber){
        carService.delete(vinNumber);
    }

}
