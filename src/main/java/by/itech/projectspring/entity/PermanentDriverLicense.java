package by.itech.projectspring.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class PermanentDriverLicense extends DriverLicense {

    private int validYears;
}
