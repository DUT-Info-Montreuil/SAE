package com.example.sae.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;


public  class Vaisseau {

    private IntegerProperty x, y;
    protected Terrain terrain;
    private int prix;
    private int portee;
    private int degat;
    private Environnement env;
    private String id;
    private static int compteur=0;
    private ObservableList<Ennemi> ennemis;

    private int vie;



    public Vaisseau(int x, int y, Terrain terrain, int prix, Environnement env, int portee, int degat) {
        this.ennemis = FXCollections.observableArrayList();
        this.prix = prix;
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.terrain = terrain;
        this.env = env;
        this.portee = portee;
        this.degat = degat;
        this.id="V"+compteur;
        compteur++;
        this.vie = 100;
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

    public int getPrix() {
        return prix;
    }

    public boolean vaisseauBienPlacee() {
        int x = getX() / 32;
        int y = getY() / 32;
        System.out.println(y + " " + x);
        if (terrain.estAInterieur(x, y) && terrain.getTileMap()[y][x] == terrain.POSEV) {
            terrain.getTileMap()[y][x] = 5;
            return true; // La case n'est pas vide
        }
        return false;
    }

    public void ennemiPorteeVaisseau(){
        for (int i = 0; i < env.getEnnemi().size(); i++){
            Ennemi a = env.getEnnemi().get(i);
            if (ennemiPortee(a)){
                ennemis.add(a);
            }
            if (!ennemiPortee(a)){
                ennemis.remove(a);
            }
        }
    }

    public int getDegat() {
        return degat;
    }

    public int getPortee() {
        return portee;
    }

    public void attaque(){
        boolean ennemi = false;
        for (int i = 0; i < ennemis.size(); i++){
                Ennemi a = ennemis.get(i);
                if (ennemiPortee(a) && a.estVivant() && ennemi == false) {
                    a.decrementerPv(degat);
                    ennemi = true;
                    System.out.println("attaque" + i);
                } else {
//                    System.out.println("pas attaque");
                }
            }
        }


    public boolean ennemiPortee(Ennemi ennemi) {
        double distance = Math.sqrt(Math.pow(ennemi.getX() - getX() , 2) + Math.pow(ennemi.getY() - getY(), 2));
        return distance <= portee;
    }

    public int getVieMax(){
        return 1000;
    }

    public int getVie() {
        return vie;
    }

    public void perteVie(){
        vie--;
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