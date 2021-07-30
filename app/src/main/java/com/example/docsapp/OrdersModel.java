package com.example.docsapp;

public class OrdersModel {
    OrdersModel(){

    }
    String Name, Price ,QTY, Image;


    public String getName() {
        return Name;
    }

    public OrdersModel(String name, String price, String QTY, String image) {
        Name = name;
        Price = price;
        this.QTY = QTY;
        Image = image;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getQTY() {
        return QTY;
    }

    public void setQTY(String QTY) {
        this.QTY = QTY;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
