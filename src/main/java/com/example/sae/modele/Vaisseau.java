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
    private String id;
    public static int compteur=0;
//    public static int j;


    public Vaisseau(int x, int y, Terrain terrain, int prix, Environnement env, int portee, int degat) {
        this.prix = prix;
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.terrain = terrain;
        this.env = env;
        this.portee = portee;
        this.degat = degat;
        this.id="V"+compteur;
        compteur++;
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
        this.yProperty().setValue(n);
    }

    public boolean vaisseauBienPlacee() {
        int x = getX() / 16;
        int y = getY() / 16;
        System.out.println(y + " " + x);
        if (terrain.getTileMap()[y][x] == terrain.POSEV) {
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
        boolean ennemi = false;
        for (int i = 0; i < e.size(); i++){
                Ennemi a = e.get(i);
                if (ennemiPortee(a) && ennemi == false) {
                    a.decrementerPv(degat);
                    ennemi = true;
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
//    boolean ennemi = false;
//    Ennemi a = e.get(j);
//        for (int i = 0; i < e.size(); i++){
//        if(ennemiPortee(a) && j!=0 && ennemi == false){
//        a.decrementerPv(degat);
//        j=i;
//        ennemi = true;
//        if (!a.estVivant() && !ennemiPortee(a)) {
//        j = 0;
//        }
//        }else {
//        a = e.get(i);
//        if (ennemiPortee(a) && ennemi == false) {
//        a.decrementerPv(degat);
//        ennemi = true;
//        j = i;
//        System.out.println("attaque");
//        } else {
//        System.out.println("pas attaque");
//        }
//        }
//        }