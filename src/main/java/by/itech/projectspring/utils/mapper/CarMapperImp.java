package by.itech.projectspring.utils.mapper;

import by.itech.projectspring.dto.CarDTO;
import by.itech.projectspring.entity.Car;
import by.itech.projectspring.entity.Minivan;
import by.itech.projectspring.entity.SportCar;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class CarMapperImp implements CarMapper {

    @Override
    public CarDTO carEntityToCarDTO(Car car) {
        if (car == null) {
            return null;
        }
        CarDTO carDTO = new CarDTO();
        carDTO.setCarType("car");
        carDTO.setModel(car.getModel());
        carDTO.setVinNumber(car.getVinNumber());
        if (car instanceof SportCar ) {
            carDTO.setMaxSpeed(((SportCar) car).getMaxSpeed());
            carDTO.setCarType("sportCar");
        }
        if (car instanceof Minivan) {
            carDTO.setSeat(((Minivan) car).getSeat());
            carDTO.setCarType("minivan");
        }
        return carDTO;
    }

    @Override
    public Car carDTOtoCarEntity(CarDTO carDTO) {
        if (carDTO == null) {
            return null;
        }
        Car car = createCar(carDTO);
        car.setModel(carDTO.getModel());
        car.setVinNumber(carDTO.getVinNumber());
        return car;
    }

    private Car createCar(CarDTO carDTO) {
        if (carDTO.getCarType().equals("sportCar")) {
            SportCar sportCar = new SportCar();
            sportCar.setMaxSpeed(carDTO.getMaxSpeed());
            return sportCar;
        }
        if (carDTO.getCarType().equals("minivan")) {
            Minivan minivan = new Minivan();
            minivan.setSeat(carDTO.getSeat());
            return minivan;
        } else return new Car();
    }
}
