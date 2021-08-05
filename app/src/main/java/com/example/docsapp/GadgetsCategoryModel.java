package com.example.docsapp;

public class GadgetsCategoryModel {
    String Image, Name;
    GadgetsCategoryModel(){
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public GadgetsCategoryModel(String image, String name) {
        this.Image = image;
        this.Name = name;
    }
}

