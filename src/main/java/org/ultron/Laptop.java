package org.ultron;

import jakarta.persistence.Embeddable;

@Embeddable
public class Laptop {
    String laptopName;
    String laptopModel;

    public String getLaptopName() {
        return laptopName;
    }

    public void setLaptopName(String laptopName) {
        this.laptopName = laptopName;
    }

    public String getLaptopModel() {
        return laptopModel;
    }

    public void setLaptopModel(String laptopModel) {
        this.laptopModel = laptopModel;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                ", laptopName='" + laptopName + '\'' +
                ", laptopModel='" + laptopModel + '\'' +
                '}';
    }
}
