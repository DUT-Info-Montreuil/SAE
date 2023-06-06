package com.example.sae.modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class BarreDeVie {
    private IntegerProperty x, y;
    private DoubleProperty vieTotale;
    private Double vie;
    private Double vieMax;
    private String id;

    public BarreDeVie(int vie, int vieMax, String id, int x, int y){
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.vie= (double) vie;
        this.vieMax = (double)vieMax;
        this.id = id;
        this.vieTotale = new SimpleDoubleProperty(vie/vieMax);
    }

    public double getVieTotale() {
        return vieTotale.getValue();
    }

    public DoubleProperty vieTotaleProperty() {
        return vieTotale;
    }

    public void setVieTotale() {
        this.vieTotale.setValue((double) vie/ (double) vieMax);
    }

    public void setVie(double vie) {
        this.vie = vie;
    }

    public IntegerProperty xProperty() {
        return x;
    }

    public IntegerProperty yProperty() {
        return y;
    }

    public String getId() {
        return id;
    }

    public final int getX() {
        return this.xProperty().getValue();
    }

    public final void setX(int n) {
        this.xProperty().setValue(n);
    }


    public final int getY() {
        return this.yProperty().getValue();
    }

    public final void setY(int n) {
        this.yProperty().setValue(n+10);
    }

    public void setId(String id) {
        this.id = id;
    }

}
