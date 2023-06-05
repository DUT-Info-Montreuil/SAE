package com.example.sae.modele;

import com.example.sae.vue.VaisseauxVue;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Environnement {
    private ObservableList<Ennemi> ennemis;
    private ObservableList<Vaisseau> vaisseaux;
    private IntegerProperty nbEnnemis;
    private int tours;
    private Terrain terrain;
    private Station station;
    private Vague vague;
    private Boutique boutique;

    public Environnement(Terrain terrain) {
        this.ennemis =FXCollections.observableArrayList();
        this.vaisseaux =FXCollections.observableArrayList();
        this.tours = 0;
        this.terrain = terrain;
        this.station = new Station(terrain, this);
        this.vague = new Vague(terrain, this);
        this.boutique = new Boutique(this);
        this.nbEnnemis = new SimpleIntegerProperty(0);

    }

    public ObservableList<Ennemi> getEnnemi() {
        return ennemis;
    }

    public void ajouterEnnemi(Ennemi e) {
        ennemis.add(e);
    }

    public ObservableList<Vaisseau> getVaisseaux() {
        return vaisseaux;
    }

    public void ajouterVaisseau(Vaisseau v) {
        vaisseaux.add(v);
    }

    public Vaisseau vaisseauPresent(int x, int y){
        for(int i=0;i<vaisseaux.size(); i++) {
            Vaisseau v = vaisseaux.get(i);
            if ((x / 32 == v.getX()/32) && (y / 32 == v.getY()/32)) {
                terrain.getTileMap()[y/32][x/32] = 4;
                boutique.ajoutVaisseau(v);
                return v;
            }
        }
        return null;
    }

    public int getArgent(){
        return boutique.getArgent();
    }
    public void suppArgent(Vaisseau vaisseau){
        boutique.suppression(vaisseau);
    }

    public IntegerProperty vieProperty() {
        return station.vieProperty();
    }

    public IntegerProperty argentProperty() {
        return boutique.argentProperty();
    }



    public IntegerProperty nbEnnemisProperty(){
        return nbEnnemis;
    }

    public final int getNbEnnemi() {
        return this.nbEnnemisProperty().getValue();
    }

    public final void setNbEnnemis() {
        if (ennemis.isEmpty()) {
            this.nbEnnemisProperty().setValue(0);
        } else {
            this.nbEnnemisProperty().setValue(ennemis.size());
        }
    }

    public int getTours() {
        return tours;
    }

    public void lancerVague(){
        vague.vagueEnnemis();
    }

    public ArrayList<Ennemi> getEnnemisVagues(){
        return vague.getEnnemisVague();
    }

    public void setCompteurVague(){
        vague.setCompteur();
    }

    public IntegerProperty compteurVagueProperty(){
        return vague.compteurProperty();
    }

    public void unTour(){
        setNbEnnemis();

        if (!vague.getEnnemisVague().isEmpty() && tours % 5 == 0) {
            ajouterEnnemi(vague.getEnnemisVague().get(0));
            vague.supprimerEnnemi();
        }

        for(int i=0;i<ennemis.size(); i++){
            Ennemi a = ennemis.get(i);
            a.seDeplace();
            if(a.estArrive()){
                ennemis.remove(i);
                station.perteVie();
            }
            if (!a.estVivant()){
                ennemis.remove(i);
                boutique.ajoutEnnemi(a);
            }
        }
        for(int i=0;i<vaisseaux.size(); i++){
            Vaisseau v = vaisseaux.get(i);
            v.perteVie();
            v.ennemiPorteeVaisseau();
            v.attaque();
        }
        this.tours++;
    }
}
