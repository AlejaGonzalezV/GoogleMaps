package com.example.reto1;

import java.io.Serializable;

public class Markers implements Serializable {

    private String name;
    private double lt;
    private double lg;

    public Markers(String name, double lt, double lg) {

        this.name = name;
        this.lt = lt;
        this.lg = lg;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public double getLt() {
        return lt;
    }

    public void setLt(double lt) {
        this.lt = lt;
    }

    public double getLg() {
        return lg;
    }

    public void setLg(double lg) {
        this.lg = lg;
    }
}

