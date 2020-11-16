package by.itech.projectspring.service;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Element<T> {
    private T obj;
    private int frequency;

    public Element(T obj) {
        this.obj = obj;
        frequency = 0;
    }
}

