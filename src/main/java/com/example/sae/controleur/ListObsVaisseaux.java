package com.example.sae.controleur;

import com.example.sae.modele.Vaisseau;
import com.example.sae.vue.VaisseauxVue;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

public class ListObsVaisseaux implements ListChangeListener<Vaisseau> {

    private Pane panneauDeJeu;
    private VaisseauxVue vaisseauxVue;

    public ListObsVaisseaux (Pane PanneauJeu) {
        this.panneauDeJeu = PanneauJeu;
        vaisseauxVue = new VaisseauxVue(PanneauJeu);
    }

    @Override
    public void onChanged(Change<? extends Vaisseau> change) {
        while (change.next()) {
            if (change.wasAdded()) {
                for (Vaisseau v : change.getAddedSubList()) {
                    System.out.println("add");
                    vaisseauxVue.creerVaisseau(v);
                }
            }
            if (change.wasRemoved()) {
                for (Vaisseau v : change.getRemoved()) {
                    System.out.println("supp");
                    vaisseauxVue.supprimerVaisseau(v);
                }
            }
        }
    }
}