package com.example.sae.controleur;

import com.example.sae.modele.RayonLaser;
import com.example.sae.vue.RayonLaserVue;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

public class ListObsRayonLaser implements ListChangeListener<RayonLaser> {

    private Pane panneauDeJeu;
    private RayonLaserVue rayonLaserVue;

    public ListObsRayonLaser(Pane PanneauJeu) {
        this.panneauDeJeu = PanneauJeu;
        rayonLaserVue = new RayonLaserVue(PanneauJeu);
    }

    @Override
    public void onChanged(Change<? extends RayonLaser> change) {
        while (change.next()) {
            if (change.wasAdded()) {
                for (RayonLaser r : change.getAddedSubList()) {
                    System.out.println("add");
                    rayonLaserVue.afficherLaser(r);
                }
            }
            if (change.wasRemoved()) {
                for (RayonLaser r : change.getRemoved()) {
                    System.out.println("supp");
                    rayonLaserVue.supprimerRayonLaser(r);
                }
            }
        }
    }
}
