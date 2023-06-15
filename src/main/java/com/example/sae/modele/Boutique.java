package com.example.sae.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Boutique {
    private IntegerProperty argent;

    public Boutique() {
        this.argent = new SimpleIntegerProperty(200);
    }

    public IntegerProperty argentProperty() {
        return argent;
    }

    public final int getArgent() {
        return this.argentProperty().getValue();
    }

    public final void setArgent(int n) {
        this.argentProperty().setValue(n);
    }

    public void ajoutPrixEnnemiTuer(Ennemi ennemi){
        setArgent(getArgent() + ennemi.getPrix());
    }

    public void ajoutPrixEnleverVaisseau(Vaisseau vaisseau){
        setArgent(getArgent() + vaisseau.getPrix()/2);
    }

    public void debiterPrixVaisseau(Vaisseau vaisseau){
        setArgent(getArgent() - vaisseau.getPrix());
    }

    public void ajoutBonusFinManche(){
        setArgent(getArgent() + 50);
    }

}
