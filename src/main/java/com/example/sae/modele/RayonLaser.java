package com.example.sae.modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class RayonLaser {
    private IntegerProperty xPointA;
    private IntegerProperty yPointA;
    private IntegerProperty xPosition;
    private IntegerProperty yPosition;
    private String id;
    public static int compteur;

    public RayonLaser(int xpointA, int ypointA, int xposition, int yposition) {
        this.xPointA = new SimpleIntegerProperty(xpointA);
        this.yPointA = new SimpleIntegerProperty(ypointA);
        this.xPosition = new SimpleIntegerProperty(xposition);
        this.yPosition = new SimpleIntegerProperty(yposition);
        this.id="R"+compteur;
        compteur++;
    }

    public IntegerProperty xPointAProperty(){
        return xPointA;
    }

    public IntegerProperty yPointAProperty(){
        return yPointA;
    }

    public IntegerProperty xPositionProperty(){
        return xPosition;
    }

    public IntegerProperty yPositionProperty(){
        return yPosition;
    }
    public void setxPointA(int xPointA) {
        this.xPointA.setValue(xPointA+16);
    }

    public void setyPointA(int yPointA) {
        this.yPointA.setValue(yPointA + 10);
    }


    public int getxPointA() {
        return xPointA.getValue();
    }

    public int getyPointA() {
        return yPointA.getValue();
    }

    public int getxPosition() {
        return xPosition.getValue();
    }

    public int getyPosition() {
        return yPosition.getValue();
    }

    public String getId() {
        return id;
    }
}

