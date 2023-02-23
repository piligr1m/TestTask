package org.martynov.spring.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "color")
    @NotEmpty(message = "color shouldn't be empty")
    @Size(min = 1, max = 40, message = "color should be between 2 and 40 characters")
    private String color;
    @Column(name = "horsepower")
    @NotEmpty(message = "horsepower shouldn't be empty")
    @Size(min = 0)
    private int horsepower;
    @Column(name = "quantity")
    @Size(min = 0)
    private int quantity;
    public Car() {
    }

    public Car(String color, int horsepower, int quantity) {
        this.color = color;
        this.horsepower = horsepower;
        this.quantity = quantity;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public int getHorsepower() {
        return horsepower;
    }
    public void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public int calculateTax() {
        return Const.BASE_RATE.getNumber()*horsepower*quantity;
    }
}
