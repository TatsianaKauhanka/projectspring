package by.itech.projectspring.persistence;

import by.itech.projectspring.bean.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {

    Optional<Car> findByVinNumber(UUID vinNumber);

}
