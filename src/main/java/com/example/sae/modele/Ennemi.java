package com.example.sae.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.awt.*;

public class Ennemi {
    private IntegerProperty x, y;
    private int vitesse;
    private Terrain terrain;
    private int pv;
    private Environnement env;
    public static int compteur=0;
    private String id;
    private int prix;
    private int index;
    private BarreDeVie barreDeVie;
    private int pvMax;

    public Ennemi(int vitesse, Terrain terrain, int pv, Environnement env, int prix, int pvMax) {
        this.pv = pv;
        this.x = new SimpleIntegerProperty(50);
        this.y = new SimpleIntegerProperty(0);
        this.vitesse = vitesse;
        this.terrain = terrain;
        this.env = env;
        this.id="E"+compteur;
        compteur++;
        this.prix = prix;
        this.index = 0;
        this.pvMax = pvMax;
        this.barreDeVie = new BarreDeVie(getPv(), getPvMax(), getId(), getX(), getY());

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

    public final void setX(double n) {
        this.xProperty().setValue(n);
    }

    public final int getY() {
        return this.yProperty().getValue();
    }

    public final void setY(double n) {
        this.yProperty().setValue(n);
    }

    public boolean estVivant() {
        return this.pv > 0;
    }

    public int getPv() {
        return pv;
    }

    public int getPvMax() {
        return pvMax;
    }

    public BarreDeVie getBarreDeVie() {
        return barreDeVie;
    }

    public void decrementerPv(int n) {
        this.pv -= n;
    }

    public boolean estArrive(){
        if (terrain.getTileMap()[getY()/32][getX()/32] == 2) {
            return true;
        }
        return false;
    }

    public void seDeplace() {
        if (index < this.env.getChemin().size() - 1) {
            Point prochaineTuile = this.env.getChemin().get(index + 1);
            double prochainePosX = prochaineTuile.getY() * 32;
            double prochainePosY = prochaineTuile.getX() * 32;

            double deltaX = prochainePosX - getX();
            double deltaY = prochainePosY - getY();

            double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

            if (distance <= vitesse) {
                // Si la distance restante est inférieure ou égale à la vitesse, on arrive à la tuile suivante
                setX(prochainePosX);
                setY(prochainePosY);
                index++;
            } else {
                // Sinon, on se déplace vers la prochaine tuile en fonction de la vitesse
                double deplacementX = (deltaX / distance) * vitesse;
                double deplacementY = (deltaY / distance) * vitesse;

                setX(getX() + deplacementX);
                setY(getY() + deplacementY);
            }
        }
    }
   /*
    public void seDeplace() {

        if (index< this.env.getChemin().size()-1){
            index++;
            setX(env.getChemin().get(index).getX()*32);
            setY(env.getChemin().get(index).getY()*32);
        }
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
        */
}
