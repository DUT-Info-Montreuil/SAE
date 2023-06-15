package com.example.sae.modele;

import javafx.beans.property.*;

public class BarreDeVie  {

    private IntegerProperty x, y;
    private DoubleProperty vieTotale;
    private Double vie;
    private Double vieMax;
    private String id;
    private StringProperty couleurStyle;


    public BarreDeVie(int vie, int vieMax, String id, int x, int y){
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.vie= (double) vie;
        this.vieMax = (double)vieMax;
        this.id = id;
        this.vieTotale = new SimpleDoubleProperty(vie/vieMax);
        couleurStyle = new SimpleStringProperty();
    }

    public void setCouleur() {
        double pourcentageVie = getVieTotale();

        if (pourcentageVie <= 0.55) {
            setCouleurStyle("-fx-accent: red;"); // Rouge pour moins de 30% de vie
        } else if (pourcentageVie <= 0.85) {
            setCouleurStyle("-fx-accent: orange;"); // Orange pour 30% à 70% de vie
        } else {
            setCouleurStyle("-fx-accent: green;"); // Vert pour plus de 70% de vie
        }
    }


    public double getVieTotale() {
        return vieTotale.getValue();
    }

    public DoubleProperty vieTotaleProperty() {
        return vieTotale;
    }

    public void miseAJourVieTotale() {
        this.vieTotale.setValue((double) vie/ vieMax);
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

    public final void setX(int x) {
        this.xProperty().setValue(x);
    }


    public final int getY() {
        return this.yProperty().getValue();
    }

    public final void setY(int y) {
        this.yProperty().setValue(y-10);
    }

    public StringProperty couleurStyleProperty() {
        return couleurStyle;
    }

    public final String getCouleurStyle() {
        return this.couleurStyleProperty().getValue();
    }

    public final void setCouleurStyle(String s) {
        this.couleurStyleProperty().setValue(s);
    }

}
