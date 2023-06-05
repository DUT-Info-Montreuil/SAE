package com.example.sae.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Boutique {
    private Environnement env;
    private IntegerProperty argent;

    public Boutique(Environnement env) {
        this.env = env;
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

    public void ajoutEnnemi(Ennemi ennemi){
        setArgent(getArgent() + ennemi.getPrix());
    }

    public void ajoutVaisseau(Vaisseau vaisseau){
        setArgent(getArgent() + vaisseau.getPrix()/2);
    }

    public void suppression(Vaisseau vaisseau){
        setArgent(getArgent() - vaisseau.getPrix());
    }

}
