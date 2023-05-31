package com.example.sae.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Environnement {
    private ObservableList<Ennemi> ennemis;
    private ObservableList<Vaisseau> vaisseaux;
    private int tours;
    private Terrain terrain;

    public Environnement(Terrain terrain) {
        this.ennemis =FXCollections.observableArrayList();
        this.vaisseaux =FXCollections.observableArrayList();
        this.tours = 0;
        this.terrain = terrain;
    }

    public ObservableList<Ennemi> getEnnemi() {
        return ennemis;
    }

    public void ajouterEnnemi(Ennemi e) {
        ennemis.add(e);
    }

    public ObservableList<Ennemi> getVaisseaux() {
        return ennemis;
    }

    public void ajouterVaisseau(Vaisseau v) {
        vaisseaux.add(v);
    }

    public void unTour(){

        this.tours++;

        if (this.tours %30 == 0) {
            ajouterEnnemi(new ChevauxAlien(this.terrain, this));
        }

        for(int i=0;i<ennemis.size(); i++){
            Ennemi e = ennemis.get(i);
            e.seDeplace();
        }
        for(int i= ennemis.size()-1; i>=0;i--){
            Ennemi a = ennemis.get(i);
            if(a.estArrive()){
                ennemis.remove(i);
            }
        }
//        this.nbToursProperty.setValue(nbToursProperty.getValue()+1);
//        System.out.println("tour " + this.nbToursProperty);
    }

}
