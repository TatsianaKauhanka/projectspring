package by.itech.projectspring.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@DiscriminatorValue("owner")
@Data
public class Owner extends User {
  private int cars;
}
