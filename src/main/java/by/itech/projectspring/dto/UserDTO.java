package by.itech.projectspring.dto;

import by.itech.projectspring.entity.Car;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class UserDTO {
    @JsonProperty
    private UUID uuid;
    @JsonProperty
    private String lastName;
    @JsonProperty
    private String firstName;
    @JsonProperty
    private List<Car> carList;
    private String userType;
    private int cars;
    private int rentDays;

}
