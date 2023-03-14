package jks;

/**
 * Created by anjunli on  2022/9/23
 **/
public class Car {
    private String doors;
    private String brand;

    public Car() {
    }

    public Car(String doors, String brand) {
        this.doors = doors;
        this.brand = brand;
    }

    public String getDoors() {
        return doors;
    }

    public void setDoors(String doors) {
        this.doors = doors;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Car{" +
                "doors='" + doors + '\'' +
                ", brand='" + brand + '\'' +
                '}';
    }
}
