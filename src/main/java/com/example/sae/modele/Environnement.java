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

    public Environnement(Terrain terrain) {
        this.ennemis =FXCollections.observableArrayList();
        this.vaisseaux =FXCollections.observableArrayList();
        this.tours = 0;
        this.terrain = terrain;
        this.station = new Station(terrain, this);
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

    public void unTour(){

        this.tours++;

        if (this.tours %30 == 0 && tours < 70) {
            ajouterEnnemi(new ChevauxAlien(this.terrain, this));
        }

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
