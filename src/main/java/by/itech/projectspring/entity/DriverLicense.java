package by.itech.projectspring.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class DriverLicense {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    private UUID licenseUuid;
    private String state;
    @OneToOne(mappedBy = "license")
    private User user;


}
