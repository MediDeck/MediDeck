package com.example.medideck;

public class UserHelperClass {
    String Name1, number1, age1, email1, pass1;

    public UserHelperClass() {
    }

    public UserHelperClass(String name1, String number1, String age1, String email1, String pass1) {
        this.Name1 = name1;
        this.number1 = number1;
        this.age1 = age1;
        this.email1 = email1;
        this.pass1 = pass1;
    }

    public String getName1() {
        return Name1;
    }

    public void setName1(String name1) {
        Name1 = name1;
    }

    public String getNumber1() {
        return number1;
    }

    public void setNumber1(String number1) {
        this.number1 = number1;
    }

    public String getAge1() {
        return age1;
    }

    public void setAge1(String age1) {
        this.age1 = age1;
    }

    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    public String getPass1() {
        return pass1;
    }

    public void setPass1(String pass1) {
        this.pass1 = pass1;
    }
}

