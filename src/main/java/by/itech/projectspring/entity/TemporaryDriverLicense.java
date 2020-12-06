package by.itech.projectspring.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class TemporaryDriverLicense  extends DriverLicense{

    private int validDays;
}
