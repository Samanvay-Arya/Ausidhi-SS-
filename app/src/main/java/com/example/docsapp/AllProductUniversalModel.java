package com.example.docsapp;

import com.firebase.ui.database.FirebaseRecyclerOptions;

public class AllProductUniversalModel {
  String Name;
    String Price;
    String Rate;
    String Discount;
    String MRP;

    public String getName() {
        return Name;
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

    public String getRate() {
        return Rate;
    }

    public void setRate(String rate) {
        Rate = rate;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String discount) {
        Discount = discount;
    }

    public String getMRP() {
        return MRP;
    }

    public void setMRP(String MRP) {
        this.MRP = MRP;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public AllProductUniversalModel(String name, String price, String rate, String discount, String MRP, String image) {
        Name = name;
        Price = price;
        Rate = rate;
        Discount = discount;
        this.MRP = MRP;
        Image = image;
    }

    String Image;
  AllProductUniversalModel(){

  }
}
