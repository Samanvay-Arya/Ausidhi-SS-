package com.example.docsapp;

public class CartModel {
    String Name, QTY, Price, Image;



    CartModel(){

    }
    public CartModel(String image) {
        Image = image;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public CartModel(String name, String QTY, String price) {
        Name = name;
        this.QTY = QTY;
        Price = price;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getQTY() {
        return QTY;
    }

    public void setQTY(String QTY) {
        this.QTY = QTY;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}
