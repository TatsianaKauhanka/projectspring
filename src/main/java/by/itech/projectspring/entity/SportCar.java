package by.itech.projectspring.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue(value = "sport_car")
@Data
public class SportCar extends Car{
    private int maxSpeed;
}
