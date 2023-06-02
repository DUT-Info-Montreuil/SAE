package com.example.sae.controleur;

import com.example.sae.modele.Ennemi;
import com.example.sae.vue.EnnemisVue;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;


public class ListObsEnnemis implements ListChangeListener<Ennemi> {


    private Pane PaneauDeJeu;
    private EnnemisVue ennemisVue;

    public ListObsEnnemis(Pane PanneauJeu) {
        this.PaneauDeJeu = PanneauJeu;
        ennemisVue = new EnnemisVue(PanneauJeu);
    }

    @Override
    public void onChanged(Change<? extends Ennemi> change) {
        while (change.next()) {
            if (change.wasAdded()) {
                for (Ennemi e : change.getAddedSubList()) {
                    System.out.println("add");
                         ennemisVue.créerSprite(e);
                    }
                }
                if (change.wasRemoved()) {
                    for (Ennemi e : change.getRemoved()) {
                        System.out.println("supp");
                            ennemisVue.supprimerSprite(e);
                    }
                }
            }
        }
    }