package com.example.sae.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;


public  class Vaisseau {

    private IntegerProperty x, y;
    protected Terrain terrain;
    private int prix;
    private int portee;
    private int degat;
    private Environnement env;

    public Vaisseau(int x, int y, Terrain terrain, int prix, Environnement env, int portee, int degat) {
        this.prix = prix;
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.terrain = terrain;
        this.env = env;
        this.portee = portee;
        this.degat = degat;
    }

    public Vaisseau(Terrain terrain) {
        this.terrain = terrain;
    }

    public IntegerProperty xProperty() {
        return x;
    }

    public IntegerProperty yProperty() {
        return y;
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
        this.yProperty().setValue(n);
    }

    public boolean vaisseauBienPlacee() {
        int x = getX() / 16;
        int y = getY() / 16;
        System.out.println(y + " " + x);
        if (terrain.getTileMap()[y][x] == 4) {
            terrain.getTileMap()[y][x] = 5;
            return true; // La case à gauche n'est pas vide
        }
        return false;
    }

    public int getDegat() {
        return degat;
    }

    public int getPortee() {
        return portee;
    }

    public void attaque(ObservableList<Ennemi> e){
        for(int i=0;i<e.size(); i++) {
            Ennemi a = e.get(i);
            if (ennemiPortee(a)) {
                a.decrementerPv(degat);
                System.out.println("attaque");
            } else {
                System.out.println("pas attaque");
            }
        }
    }

    public boolean ennemiPortee(Ennemi ennemi) {
        double distance = Math.sqrt(Math.pow(ennemi.getX() - getX() , 2) + Math.pow(ennemi.getY() - getY(), 2));
        return distance <= portee;
        }

}