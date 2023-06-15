package com.example.sae.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import java.util.ArrayList;

public class Vague{

    private Terrain terrain;
    private Environnement env;
    private IntegerProperty compteurVague;
    private ArrayList<Ennemi> ennemis;
    private Boutique boutique;

    public Vague(Terrain terrain, Environnement env, Boutique boutique){
        this.compteurVague = new SimpleIntegerProperty(0);
        this.terrain = terrain;
        this.env = env;
        this.ennemis = new ArrayList<>();
        this.boutique = boutique;
    }

    public void vagueEnnemis(){
        for(int i = 0; i < getCompteurVague() ; i++) {
                ennemis.add(new LimaceLente(terrain, env));
                ennemis.add(new Alien(terrain, env));
                ennemis.add(new ChevauxAlien(terrain, env));
        }
    }

    public IntegerProperty compteurVagueProperty(){
        return compteurVague;
    }

    public int getCompteurVague(){
        return this.compteurVagueProperty().getValue();
    }

    public void incrementerCompteur() {
        this.compteurVagueProperty().setValue(getCompteurVague()+1);
        if (getCompteurVague()>1){
        boutique.ajoutBonusFinManche();
        }
    }

    public ArrayList<Ennemi> getEnnemisVague() {
        return ennemis;
    }

    public void supprimerEnnemi() {
        ennemis.remove(0);
    }
}
