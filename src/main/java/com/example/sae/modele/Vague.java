package com.example.sae.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Vague{
    private Terrain terrain;
    private Environnement env;
    private IntegerProperty compteur;
    private ArrayList<Ennemi> ennemis;
    private Boutique boutique;

    public Vague(Terrain terrain, Environnement env, Boutique boutique){
        this.compteur = new SimpleIntegerProperty(0);
        this.terrain = terrain;
        this.env = env;
        this.ennemis = new ArrayList<>();
        this.boutique = boutique;
    }

    public void vagueEnnemis(){
        for(int i = 0; i < getCompteur() ; i++) {

                ennemis.add(new LimaceLente(terrain, env));
                ennemis.add(new Alien(terrain, env));
                ennemis.add(new ChevauxAlien(terrain, env));


        }
    }

    public IntegerProperty compteurProperty(){
        return compteur;
    }

    public int getCompteur(){
        return this.compteurProperty().getValue();
    }

    public void setCompteur() {
        this.compteurProperty().setValue(getCompteur()+1);
        if (getCompteur()>1){
        boutique.ajoutManche();
        }
    }

    public ArrayList<Ennemi> getEnnemisVague() {
        return ennemis;
    }

    public void supprimerEnnemi() {
        ennemis.remove(0);
    }
}
