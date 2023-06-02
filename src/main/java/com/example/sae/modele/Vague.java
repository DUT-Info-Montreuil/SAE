package com.example.sae.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Vague {
    private Terrain terrain;
    private Environnement env;
    private ObservableList<Ennemi> ennemis;
    private static int compteur=0;

    public Vague(Terrain terrain, Environnement env){
        this.terrain = terrain;
        this.env = env;
        this.ennemis= FXCollections.observableArrayList();
    }

    public void ennemis(){
        for(int i = 0; i <= compteur ; i++) {
            if (env.getTours() %5 == 0 && env.getTours() < 30) {
                env.getEnnemi().add(new LimaceLente(terrain, env));
                System.out.println("caca");
            }
            if (this.env.getTours() %10 == 0 && env.getTours() < 30) {
                env.getEnnemi().add(new Alien(terrain, env));
            }
            if (this.env.getTours() %15 == 0 && env.getTours() < 30) {
                env.getEnnemi().add(new ChevauxAlien(terrain, env));
            }
            }
        compteur++;
    }

    public int getCompteur(){
        return compteur;
    }
}
