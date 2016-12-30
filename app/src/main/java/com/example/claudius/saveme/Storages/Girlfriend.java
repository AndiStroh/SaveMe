package com.example.claudius.saveme.Storages;

import android.graphics.Bitmap;

import java.sql.Date;

/**
 * Created by Claudius on 19.12.16.
 */


//Dient als Speicher für die ganzen Daten der Freundin während der Laufzeit. Informationen werden jeweils beim beenden des Erstellungsprozesses und beim öffenen der App
    //Frisch aus dem Speicher geladen oder geschrieben.
public class Girlfriend {

    private String name;
    private String residence;
    private Bitmap profilepic;
    private String color;
    private String band;
    private String flowers;
    private String food;

    //private Date bDay;
    //private Date anniversary;

    private int bDayDay;
    private int bDayMonth;
    private int bDayYear;
    private int anniversaryDay;
    private int anniversaryMonth;
    private int anniversaryYear;

    /*
    public Date getBDay(){return bDay; }

    public void setBDay(Date newBDay){this.bDay = newBDay; }

    public Date getAnniversary(){return anniversary; }

    public void setAnniversary(Date newAnniversary){this.anniversary = newAnniversary; }
    */

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public String getFlowers() {
        return flowers;
    }

    public void setFlowers(String flowers) {
        this.flowers = flowers;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public int getbDayDay() {
        return bDayDay;
    }

    public void setbDayDay(int bDayDay) {
        this.bDayDay = bDayDay;
    }

    public int getbDayMonth() {
        return bDayMonth;
    }

    public void setbDayMonth(int bDayMonth) {
        this.bDayMonth = bDayMonth;
    }

    public int getAnniversaryDay() {
        return anniversaryDay;
    }

    public void setAnniversaryDay(int anniversaryDay) {
        this.anniversaryDay = anniversaryDay;
    }

    public int getAnniversaryMonth() {
        return anniversaryMonth;
    }

    public void setAnniversaryMonth(int anniversaryMonth) { this.anniversaryMonth = anniversaryMonth; }

    public int getbDayYear() {
        return bDayYear;
    }

    public void setbDayYear(int bDayYear) {
        this.bDayYear = bDayYear;
    }

    public int getAnniversaryYear() {
        return anniversaryYear;
    }

    public void setAnniversaryYear(int anniversaryYear) {
        this.anniversaryYear = anniversaryYear;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public Bitmap getProfilePic() {
        return profilepic;
    }

    public void setProfilePic(Bitmap profilepic) {
        this.profilepic = profilepic;
    }
}
