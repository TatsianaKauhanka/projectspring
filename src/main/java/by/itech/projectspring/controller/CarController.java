package by.itech.projectspring.controller;


import by.itech.projectspring.dto.CarDTO;
import by.itech.projectspring.entity.Car;
import by.itech.projectspring.service.VehicleServiceI;

import by.itech.projectspring.utils.mapper.CarMapper;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/vehicle")
@RequiredArgsConstructor
public class CarController {
    private final VehicleServiceI<Car> carService;
    private final CarMapper carMapper;

    @PostMapping("/{subtype}")
    protected @ResponseBody
    CarDTO doPost(@RequestBody CarDTO carDTO, @PathVariable String subtype) {
        log.debug("Car {} is saving", carDTO);
        carDTO.setCarType(subtype);
        Car carFromDTO = carMapper.carDTOtoCarEntity(carDTO);
        Car carFromBD = carService.save(carFromDTO);
        CarDTO carDTOFromBD = carMapper.carEntityToCarDTO(carFromBD);
        log.debug("Car{} has been saved", carFromDTO.getVinNumber());
        return carDTOFromBD;
    }

    @GetMapping(value = "/{vinNumber}")
    protected CarDTO doGet(@PathVariable("vinNumber") UUID vinNumber) {
        log.debug("Getting car with vinNumber {}", vinNumber);
        CarDTO carDTOFromBD = carMapper.carEntityToCarDTO(carService.get(vinNumber));
        log.debug("Car with vinNumber {} has been gotten from BD", vinNumber);
        return carDTOFromBD;
    }

    @PutMapping(value = "/{subtype}/{vinNumber}")
    protected void doPut(@RequestBody CarDTO carDTO, @PathVariable UUID vinNumber, @PathVariable String subtype) {
        log.debug("Updating car with vin Number {}", vinNumber);
        carDTO.setVinNumber(vinNumber);
        carDTO.setCarType(subtype);
        Car carFromDTO = carMapper.carDTOtoCarEntity(carDTO);
        carService.update(carFromDTO);
        log.debug("Car with vin number {} has been updated", carDTO.getVinNumber());
    }

    @DeleteMapping(value = "/{vinNumber}")
    protected void doDelete(@PathVariable("vinNumber") UUID vinNumber) {
        log.debug("Deleting car with vin Number {}", vinNumber);
        carService.delete(vinNumber);
        log.debug("Car with vin number {} has been deleted", vinNumber);
    }

}
