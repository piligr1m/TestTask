package org.martynov.spring.models;

public enum Const {
    BASE_RATE(100);
    private int number;
    Const(int number)
    {
        this.number = number;
    }
    public int getNumber() {
        return number;
    }
}
