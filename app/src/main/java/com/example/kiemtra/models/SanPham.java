package com.example.kiemtra.models;
public class SanPham {
    private int id;
    private String name;
    private float price;
    private String image;

    public SanPham() {

    }
    public SanPham(String name, float price, String image ) {

        this.name = name;
        this.price = price;
        this.image = image;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
