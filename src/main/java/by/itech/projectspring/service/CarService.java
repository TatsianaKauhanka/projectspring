package by.itech.projectspring.service;

import by.itech.projectspring.entity.Car;
import by.itech.projectspring.entity.Minivan;
import by.itech.projectspring.entity.SportCar;
import by.itech.projectspring.repository.CarRepository;
import by.itech.projectspring.utils.exceptions.CarNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;


@Slf4j
@Service("carService")
@RequiredArgsConstructor
public class CarService implements VehicleServiceI<Car> {

    private final CarRepository carRepository;

    public Car get(UUID vinNumber) {
        log.debug("Getting car with vin Number {} in service", vinNumber);
        Car car = carRepository.findByVinNumber(vinNumber).orElseThrow(CarNotFoundException::new);
        log.debug("Car with vinNumber {} has been gotten from BD in service", vinNumber);
        return car;
    }

    @Transactional
    public void delete(UUID vinNumber) {
        log.debug("Deleting car with vin number {} in service", vinNumber);
        carRepository.deleteByVinNumber(vinNumber);
        log.debug("Car with vin number {} was deleted in service", vinNumber);
    }

    public Car save(Car car) {
        log.debug("Saving car {} in service", car);
        car.setVinNumber(UUID.randomUUID());
        Car carFromDB = carRepository.save(car);
        log.debug("Car with vin number {} has been saved",carFromDB.getVinNumber());
        return carFromDB;
    }

    public void update(Car car) {
        log.debug("Car with vin number {} is updating in service", car.getVinNumber());

        if(car instanceof Minivan){
            Minivan carFromDB = (Minivan) get(car.getVinNumber());
            carFromDB.setSeat(((Minivan) car).getSeat());
        }
        if(car instanceof SportCar){
            SportCar carFromDB = (SportCar) get(car.getVinNumber());
            carFromDB.setMaxSpeed(((SportCar)car).getMaxSpeed());
        }
        Car carFromDB = get(car.getVinNumber());
        carFromDB.setModel(car.getModel());
        carRepository.save(carFromDB);
        log.debug("Car with vin number {} has been updated", car.getVinNumber());
    }
}
