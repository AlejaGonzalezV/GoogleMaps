package com.example.reto1;

import java.io.Serializable;

public class Marker implements Serializable {

    private String name;
    private double lt;
    private double lg;
    private double dist;



    public Marker(String name, double lt, double lg) {
        this.name = name;
        this.lt = lt;
        this.lg = lg;
    }

    public double getDist() {
        return dist;
    }

    public void setDist(double dist) {
        this.dist = dist;
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
