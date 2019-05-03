package com.example.boilermaintenance;

public class ProductGS {
    private int id;
    private int quantity;
    private String name;

    public ProductGS(int id, int quantity, String name) {
        this.id = id;
        this.quantity = quantity;
        this.name=name;

    }
    public String getName() {
        return name;
    }


    public int getId() {
        return id;
    }

    /*public void setId(int id) {
        this.id = id;
    }*/

    public int getQuantity() {
        return quantity;
    }
}
