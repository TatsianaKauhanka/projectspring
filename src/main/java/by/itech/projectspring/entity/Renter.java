package by.itech.projectspring.entity;


import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("renter")
@Data
public class Renter  extends User {
    private int rentDays;
}
