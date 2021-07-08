package com.example.medideck;

public class User2 {

    String name1, name2, name3, name4, name5,timekey;

    public User2() {
    }

    public User2(String name1, String name2, String name3, String name4, String name5, String timekey) {
        this.name1 = name1;
        this.name2 = name2;
        this.name3 = name3;
        this.name4 = name4;
        this.name5 = name5;
        this.timekey = timekey;
    }

    public String getTimekey() { return timekey;}

    public String getName1() {
        return name1;
    }

    public String getName2() {
        return name2;
    }

    public String getName3() {
        return name3;
    }

    public String getName4() {
        return name4;
    }

    public String getName5() {
        return name5;
    }
}