package ru.job4j.serialization.java;

import java.io.Serializable;
import com.sun.xml.txw2.annotation.XmlElement;
import javax.xml.bind.annotation.*;

@XmlElement(value = "wheel")
public class Wheel implements Serializable {
    @XmlAttribute
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
