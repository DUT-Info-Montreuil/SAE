package com.example.sae.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Station {

    private IntegerProperty vie;

    public Station() {
        this.vie = new SimpleIntegerProperty(20);
    }

    public void decrementerVie(){
        this.vieProperty().setValue(getVie()-1);
}

    public int getVie(){
        return vieProperty().getValue();
    }

    public IntegerProperty vieProperty() {
        return vie;
    }
}
