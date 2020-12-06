package by.itech.projectspring.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue(value = "minivan")
@Data
public class Minivan extends Car{
    private int seat;
}
