package com.example.docsapp;

public class NotificationModel {
    NotificationModel(){

    }
    String Title;
    String Time;
    String Image;



    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public NotificationModel(String title, String time,String image) {
        Title = title;
        Time = time;
        Image=image;
    }
}
