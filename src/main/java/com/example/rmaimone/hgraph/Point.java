package com.example.rmaimone.hgraph;

/**
 * Created by rmaimone on 13/09/2016.
 */
public class Point {

    public double coordinateX;
    public double coordinateY;
    public String label;

    public Point(double coordinateX, double coordinateY, String label) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.label = label;
    }

    public double getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(double coordinateX) {
        this.coordinateX = coordinateX;
    }

    public double getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(double coordinateY) {
        this.coordinateY = coordinateY;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
