package com.example.sae.vue;

import com.example.sae.modele.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class RayonLaserVue {

    private Pane panneauJeu;

    public RayonLaserVue (Pane panneauJeu) {
        this.panneauJeu = panneauJeu;
    }

    public void afficherLaser(RayonLaser rayonLaser){
        Line line = new Line();
        line.setStroke(Color.RED);
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.ORANGERED);
        line.setEffect(dropShadow);
        line.setStrokeWidth(4.0);
        line.setId(rayonLaser.getId());
        line.startXProperty().bind(rayonLaser.xPositionVaisseauProperty());
        line.startYProperty().bind(rayonLaser.yPositionVaisseauProperty());
        line.endXProperty().bind(rayonLaser.xPositionEnnemiProperty());
        line.endYProperty().bind(rayonLaser.yPositionEnnemiProperty());
        this.panneauJeu.getChildren().add(line);
    }

    public void supprimerRayonLaser(RayonLaser rayonLaser){
        this.panneauJeu.getChildren().remove(this.panneauJeu.lookup("#"+ rayonLaser.getId()));
    }
}


