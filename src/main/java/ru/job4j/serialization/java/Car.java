package ru.job4j.serialization.java;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class Car implements Serializable {
    @XmlAttribute
    private int speed;
    @XmlAttribute
    private boolean isSell;
    @XmlAttribute
    private String type;
    private Wheel spareWheels;
    private Wheel[] wheels;

    Car() {   }

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

    public static void main(String[] args) throws JAXBException {
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

        JAXBContext context = JAXBContext.newInstance(Car.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(serializedCar, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Car result = (Car) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}
