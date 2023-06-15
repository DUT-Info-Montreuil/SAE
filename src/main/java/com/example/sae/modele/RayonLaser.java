package com.example.sae.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class RayonLaser {
    private IntegerProperty xPositionEnnemi;
    private IntegerProperty yPositionEnnemi;
    private IntegerProperty xPositionVaisseau;
    private IntegerProperty yPositionVaisseau;
    private String id;
    public static int compteurId;

    public RayonLaser(int xPositionEnnemi, int yPositionEnnemi, int xPositionVaisseau, int yPositionVaisseau) {
        this.xPositionEnnemi = new SimpleIntegerProperty(xPositionEnnemi);
        this.yPositionEnnemi = new SimpleIntegerProperty(yPositionEnnemi);
        this.xPositionVaisseau = new SimpleIntegerProperty(xPositionVaisseau + 16);
        this.yPositionVaisseau = new SimpleIntegerProperty(yPositionVaisseau + 16);
        this.id="R"+ compteurId;
        compteurId++;
    }

    public IntegerProperty xPositionEnnemiProperty(){
        return xPositionEnnemi;
    }

    public IntegerProperty yPositionEnnemiProperty(){
        return yPositionEnnemi;
    }

    public IntegerProperty xPositionVaisseauProperty(){
        return xPositionVaisseau;
    }

    public IntegerProperty yPositionVaisseauProperty(){
        return yPositionVaisseau;
    }

    public void setxPositionEnnemi(int xPositionEnnemi) {
        this.xPositionEnnemi.setValue(xPositionEnnemi +16);
    }

    public void setyPositionEnnemi(int yPositionEnnemi) {
        this.yPositionEnnemi.setValue(yPositionEnnemi + 10);
    }

    public String getId() {
        return id;
    }
}

