package by.itech.projectspring.utils.mapper;

import by.itech.projectspring.dto.CarDTO;
import by.itech.projectspring.entity.Car;

import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CarMapper {
    CarDTO carEntityToCarDTO(Car car);

    Car carDTOtoCarEntity(CarDTO carDTO);
}

