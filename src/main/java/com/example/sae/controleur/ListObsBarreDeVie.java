package com.example.sae.controleur;

import com.example.sae.modele.BarreDeVie;
import com.example.sae.modele.Ennemi;
import com.example.sae.vue.BarreDeVieVue;
import com.example.sae.vue.EnnemisVue;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;


public class ListObsBarreDeVie implements ListChangeListener<BarreDeVie> {


    private Pane PaneauDeJeu;
    private BarreDeVieVue barreDeVieVue;

    public ListObsBarreDeVie(Pane PanneauJeu) {
        this.PaneauDeJeu = PanneauJeu;
        barreDeVieVue = new BarreDeVieVue(PanneauJeu);
    }

    @Override
    public void onChanged(Change<? extends BarreDeVie> change) {
        while (change.next()) {
            if (change.wasAdded()) {
                for (BarreDeVie b : change.getAddedSubList()) {
                    System.out.println("add");
                    barreDeVieVue.afficherBarreVie(b);
                }
            }
        }
    }
}
