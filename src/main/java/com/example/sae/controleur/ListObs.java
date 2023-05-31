package com.example.sae.controleur;

import com.example.sae.modele.Ennemi;
import com.example.sae.modele.Terrain;
import javafx.collections.ListChangeListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class ListObs implements ListChangeListener<Ennemi> {

    private Image imageEnn;
    private Terrain terrain;
    private Pane PaneauDeJeu;

    @Override
    public void onChanged(Change<? extends Ennemi> change) {
        if (change.wasRemoved()) {
            // Suppression des acteurs supprimés
            for (Ennemi personnage : change.getRemoved()) {
//                System.out.println("il est mort");
                ImageView iv2 = new ImageView(imageEnn);
//                iv2.translateXProperty().bind(personnage.xProperty());
//                iv2.translateYProperty().bind(personnage.yProperty());
//                if(personnage.arrive()) {
//                    this.PaneauDeJeu.getChildren().remove(iv2);
                }
            }
        }
    }

