package com.example.sae.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Environnement {
    private ObservableList<Ennemi> ennemis;
    private ObservableList<Vaisseau> vaisseaux;

    public Environnement() {
        this.ennemis =FXCollections.observableArrayList();
        this.vaisseaux =FXCollections.observableArrayList();
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

    public void ajouterVaisseaugit(Ennemi e) {
        ennemis.add(e);
    }
}
