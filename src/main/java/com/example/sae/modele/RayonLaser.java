package com.example.sae.modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class RayonLaser {
    private IntegerProperty xPointA;
    private IntegerProperty yPointA;
    private DoubleProperty longueur;
    private IntegerProperty xPosition;
    private IntegerProperty yPosition;
    private DoubleProperty angle;
    private String id;
    public static int compteur;

    public RayonLaser(int xpointA, int ypointA, int xposition, int yposition) {
        this.xPointA = new SimpleIntegerProperty(xpointA);
        this.yPointA = new SimpleIntegerProperty(ypointA);
        this.xPosition = new SimpleIntegerProperty(xposition);
        this.yPosition = new SimpleIntegerProperty(yposition);
        this.longueur = new SimpleDoubleProperty(calculerDistance());
        this.angle = new SimpleDoubleProperty(calculerAngle());
        this.id="R"+compteur;
        compteur++;
    }

    public double calculerAngle(){
        double delta_x = getxPosition() - getxPointA();
        double delta_y = getyPosition() - getyPointA();
        double angle = Math.atan2(delta_y, delta_x);
        return Math.toDegrees(angle);
    }

    private double calculerDistance() {
        double delta_x = getxPosition() - getxPointA();
        double delta_y = getyPosition() - getyPointA();
        return Math.sqrt(delta_x * delta_x + delta_y * delta_y);
    }

    public IntegerProperty xPointAProperty(){
        return xPointA;
    }

    public IntegerProperty yPointAProperty(){
        return yPointA;
    }

    public double getAngle() {
        return angle.getValue();
    }

    public DoubleProperty distanceProperty(){
        return angle;
    }

    public DoubleProperty angleProperty(){
        return angle;
    }

    public IntegerProperty xPositionProperty(){
        return xPosition;
    }
    public IntegerProperty yPositionProperty(){
        return yPosition;
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

