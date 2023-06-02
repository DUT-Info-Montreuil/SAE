package com.example.sae.modele;

import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Environnement {
    private ObservableList<Ennemi> ennemis;
    private ObservableList<Vaisseau> vaisseaux;
    private int tours;
    private Terrain terrain;
    private Station station;
    private Vague vague;

    public Environnement(Terrain terrain) {
        this.ennemis =FXCollections.observableArrayList();
        this.vaisseaux =FXCollections.observableArrayList();
        this.tours = 0;
        this.terrain = terrain;
        this.station = new Station(terrain, this);
        this.vague = new Vague(terrain, this);
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

    public IntegerProperty vieProperty() {
        return station.vieProperty();
    }

    public int getTours() {
        return tours;
    }

    public void unTour(){

        if (vague.getCompteur() ==0) {
            vague.ennemis();
        }

        this.tours++;


        for(int i=0;i<ennemis.size(); i++){
            Ennemi e = ennemis.get(i);
            e.seDeplace();
        }
        for(int i=0;i<ennemis.size(); i++){
            Ennemi a = ennemis.get(i);
            if(a.estArrive()){
                ennemis.remove(i);
                station.perteVie();
            }
            if (!a.estVivant()){
                ennemis.remove(i);
            }
        }
        for(int i=0;i<vaisseaux.size(); i++){
            Vaisseau v = vaisseaux.get(i);
            v.attaque(getEnnemi());
        }

//        this.nbToursProperty.setValue(nbToursProperty.getValue()+1);
//        System.out.println("tour " + this.nbToursProperty);
    }

}
