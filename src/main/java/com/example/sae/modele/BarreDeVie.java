package com.example.sae.modele;

import javafx.beans.property.*;
import javafx.scene.control.ProgressBar;

public class BarreDeVie  {
    private IntegerProperty x, y;
    private DoubleProperty vieTotale;
    private Double vie;
    private Double vieMax;
    private String id;
    private StringProperty style;


    public BarreDeVie(int vie, int vieMax, String id, int x, int y){
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.vie= (double) vie;
        this.vieMax = (double)vieMax;
        this.id = id;
        this.vieTotale = new SimpleDoubleProperty(vie/vieMax);
        style = new SimpleStringProperty();
    }

    public void setCouleur() {
        double pourcentageVie = getVieTotale();

        if (pourcentageVie <= 0.55) {
            setStyle("-fx-accent: red;"); // Rouge pour moins de 30% de vie
        } else if (pourcentageVie <= 0.85) {
            setStyle("-fx-accent: orange;"); // Orange pour 30% à 70% de vie
        } else {
            setStyle("-fx-accent: green;"); // Vert pour plus de 70% de vie
        }
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
        this.yProperty().setValue(n-10);
    }

    public StringProperty styleProperty() {
        return style;
    }

    public final String getStyle() {
        return this.styleProperty().getValue();
    }

    public final void setStyle(String s) {
        this.styleProperty().setValue(s);
    }

}
