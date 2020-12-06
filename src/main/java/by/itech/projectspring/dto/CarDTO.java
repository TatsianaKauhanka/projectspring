package by.itech.projectspring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class CarDTO {
    @JsonProperty
    private String model;
    @JsonProperty
    private UUID vinNumber;
    @JsonProperty
    private int maxSpeed;
    @JsonProperty
    private int seat;
    private String carType;
}
