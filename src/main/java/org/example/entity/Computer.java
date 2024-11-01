package org.example.entity;

public class Computer {
    private int computerId;
    private String brand;

    public Computer(int computerId, String brand) {
        this.computerId = computerId;
        this.brand = brand;
    }

    public int getComputerId() {
        return computerId;
    }

    public void setComputerId(int computerId) {
        this.computerId = computerId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Computer [computerId=" + computerId + ", brand" + brand + " ]";
    }
}


