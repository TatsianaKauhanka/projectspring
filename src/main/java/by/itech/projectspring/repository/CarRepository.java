package by.itech.projectspring.repository;

import by.itech.projectspring.entity.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {

    Optional<Car> findByVinNumber(UUID vinNumber);
    void deleteByVinNumber(UUID vin);

}
