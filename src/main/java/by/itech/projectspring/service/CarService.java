package by.itech.projectspring.service;

import by.itech.projectspring.bean.Car;
import by.itech.projectspring.persistence.CarRepository;
import by.itech.projectspring.utils.exceptions.CarNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service("carService")
@RequiredArgsConstructor
public class CarService implements CarServiceInterface<Car> {

    private final CarRepository carRepository;

    public Car get(UUID vinNumber) {
        return carRepository.findByVinNumber(vinNumber).orElseThrow(CarNotFoundException::new);
    }

    @Transactional
    public void delete(UUID vinNumber) {
        carRepository.findByVinNumber(vinNumber)
                .ifPresent(this::deleteAction);
    }

    private void deleteAction(Car car) {
        carRepository.delete(car);
    }

    public Car save(Car car) {
        car.setVinNumber(UUID.randomUUID());
        return carRepository.save(car);
    }

    public void update(Car car) {
        Car carFromDB = get(car.getVinNumber());
        carFromDB.setModel(car.getModel());
        carRepository.save(carFromDB);
    }
}
