package com.example.claudius.saveme.Storages;




//Es macht Sinn für den user eine nue Klasse zu erstellen, sonst müssten wir Freundin und Nutzer vermischen
public class User {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
