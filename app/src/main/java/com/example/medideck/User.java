package com.example.medideck;

public class User {

    String D_Hospital, D_charges, D_days, D_edulevel, D_speciality, D_time,D_name;

    public User() {
    }

    public User(String d_Hosspital, String d_charges, String d_days, String d_edulevel, String d_speciality, String d_time, String d_name) {
        D_Hospital = d_Hosspital;
        D_charges = d_charges;
        D_days = d_days;
        D_edulevel = d_edulevel;
        D_speciality = d_speciality;
        D_time = d_time;
        D_name = d_name;
    }

    public String getD_Hospital() {
        return D_Hospital;
    }

    public String getD_charges() {
        return D_charges;
    }

    public String getD_days() {
        return D_days;
    }

    public String getD_edulevel() {
        return D_edulevel;
    }

    public String getD_speciality() {
        return D_speciality;
    }

    public String getD_time() {
        return D_time;
    }

    public String getD_name() {
        return D_name;
    }
}
