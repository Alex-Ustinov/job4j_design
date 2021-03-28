package ru.job4j.serialization.java;

import java.io.Serializable;

public class Wheel implements Serializable {
    private String material;

    Wheel(String material) {
        this.material = material;
    }

    @Override
    public String toString() {
        return "Wheel{"
                + "material='" + material + '\''
                + '}';
    }
}
