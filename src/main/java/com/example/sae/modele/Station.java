package com.example.sae.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Station {

    private IntegerProperty vie;

    private  Terrain terrain;

    private Environnement environnement;


    public Station(Terrain terrain, Environnement environnement) {
        this.vie = new SimpleIntegerProperty(20);
        this.terrain = terrain;
        this.environnement = environnement;
    }

    public void perteVie (){
        this.vieProperty().setValue(getVie()-1);
}

    public int getVie(){
        return vieProperty().getValue();
    }

    public IntegerProperty vieProperty() {
        return vie;
    }
}
