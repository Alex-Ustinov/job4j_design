package ru.job4j.serialization.java;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Serializable;
import java.util.Arrays;

public class Car implements Serializable {
    private int speed;
    private boolean isSell;
    private String type;
    private Wheel spareWheels;
    private Wheel[] wheels;

    Car(int speed, Boolean isSell, String type, Wheel spareWheels, Wheel[] wheels) {
        this.speed = speed;
        this.isSell = isSell;
        this.type = type;
        this.spareWheels = spareWheels;
        this.wheels = wheels;
    }

    @Override
    public String toString() {
        return "Car{"
               + "speed=" + speed
               + ", isSell=" + isSell
               + ", type='" + type + '\''
               + ", spareWheels=" + spareWheels
               + ", wheels=" + Arrays.toString(wheels)
               + '}';
    }

    public static void main(String[] args) {
        Wheel spareWheels = new Wheel("protected");
        Wheel[] wheels =  new Wheel[] {new Wheel("plain")};
        final Car serializedCar = new Car(111, false, "Honda", spareWheels, wheels);
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(serializedCar));
        final String jsonCar =
                "{"
                + "speed=" + 111
                + ", isSell=" + false
                + ", type='" + "Honda" + '\''
                + ", spareWheels="
                        + "{"
                        +   "material='" + "protected" + '\''
                        + '}'
                + ", wheels="
                    + "["
                        + "{"
                        +   "material='" + "plain" + '\''
                        + '}'
                    + "]"
                + '}';
        final Car routeBackCar = gson.fromJson(jsonCar, Car.class);
        System.out.println(routeBackCar);
    }
}
