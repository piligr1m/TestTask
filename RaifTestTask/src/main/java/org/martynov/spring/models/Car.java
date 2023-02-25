package org.martynov.spring.models;

public class Car {
    private String color;
    private int horsepower;
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
