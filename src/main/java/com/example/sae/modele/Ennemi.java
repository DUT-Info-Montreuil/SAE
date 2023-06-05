package com.example.sae.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Ennemi {
    private IntegerProperty x, y;
    private int v; // vitesse de deplacement
    protected Terrain terrain;
    private int pv;
    private int ligne; // TODO voir si c'est redondant. Si oui, supprimer
    private int colonne;
    private int compteurPx;
    private int direction;
    private Environnement env;
    public static int compteur=0;
    private String id;
    private int prix;

    public Ennemi(int v, Terrain terrain, int pv, Environnement env, int prix) {
        this.pv = pv;
        this.x = new SimpleIntegerProperty(32);
        this.y = new SimpleIntegerProperty(0);
        this.v = v;
        this.terrain = terrain;
        this.ligne = 0;
        this.colonne = 1;
        this.compteurPx = 0;
        this.direction = 0;
        this.env = env;
        this.id="E"+compteur;
        compteur++;
        this.prix = prix;

    }
    public String getId() {
        return id;
    }

    public IntegerProperty xProperty() {
        return x;
    }

    public IntegerProperty yProperty() {
        return y;
    }

    public int getPrix(){
        return prix;
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

    public boolean estVivant() {
        return this.pv > 0;
    }

    public void meurt() {
        this.pv = 0;
    }


    public void decrementerPv(int n) {
        this.pv -= n;
    }

    public boolean estArrive(){
        if (terrain.getTileMap()[ligne][colonne] == 2) {
            return true;
        }
        return false;
    }

    public void seDeplace() {
        if (terrain.getTileMap()[ligne][colonne] == terrain.STATION) {
            setX(this.getX());
        } else {
            if (terrain.getTileMap()[ligne][colonne + 1] == terrain.CHEMIN && direction != 4 || terrain.getTileMap()[ligne][colonne + 1] == terrain.STATION) {
                setX(this.getX() + v);
                compteurPx = compteurPx + v;
                if (compteurPx >= 32) {
                    compteurPx = 0;
                    colonne++;
                    direction = 6;
                }
            }else {
                if (terrain.getTileMap()[ligne][colonne - 1] == terrain.CHEMIN && direction != 6 || terrain.getTileMap()[ligne][colonne - 1] == terrain.STATION) {
                    setX(this.getX() - v);
                    compteurPx = compteurPx + v;
                    if (compteurPx >= 32) {
                        compteurPx = 0;
                        colonne--;
                        direction = 4;
                    }
                }else {
                    if (terrain.getTileMap()[ligne + 1][colonne] == terrain.CHEMIN && direction != 8 || terrain.getTileMap()[ligne + 1][colonne] == terrain.STATION) {
                        setY(this.getY() + v);
                        compteurPx = compteurPx + v;
                        if (compteurPx >= 32) {
                            compteurPx = 0;
                            ligne++;
                            direction = 2;
                        }
                    } else {
                        if (terrain.getTileMap()[ligne - 1][colonne] == terrain.CHEMIN && direction != 2 || terrain.getTileMap()[ligne - 1][colonne] == terrain.STATION) {
                            setY(this.getY() - v);
                            compteurPx = compteurPx + v;
                            if (compteurPx >= 32) {
                                compteurPx = 0;
                                ligne--;
                                direction = 8;
                            }
                        }
                    }
                }
            }
        }

    }
}