package com.example.sae.vue;

import com.example.sae.Main;
import com.example.sae.modele.*;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.net.URL;
import java.util.ArrayList;

public class RayonLaserVue {
    private Pane panneauJeu;
    private Image imageLaser ;
    private ImageView iv2;

    public RayonLaserVue (Pane panneauJeu) {
        this.panneauJeu = panneauJeu;
        URL urlImageLaser = Main.class.getResource("laser.png");
        imageLaser = new Image(String.valueOf(urlImageLaser));

    }

    public void afficherLaser(RayonLaser rayonLaser){
/*        iv2 = new ImageView(imageLaser);
        iv2.setId(rayonLaser.getId());
        iv2.translateXProperty().bind(rayonLaser.xPositionProperty());
        iv2.translateYProperty().bind(rayonLaser.yPositionProperty());
        iv2.rotateProperty().bind(rayonLaser.angleProperty()); // Lier la rotation à la propriété angle de RayonLaser
        iv2.fitHeightProperty().bind(rayonLaser.distanceProperty());
        iv2.fitWidthProperty().bind(rayonLaser.distanceProperty());
        this.panneauJeu.getChildren().add(iv2);*/
        Line line = new Line();
        line.setStroke(Color.RED);
        line.setStrokeWidth(4.0);
        line.setId(rayonLaser.getId());
        line.startXProperty().bind(rayonLaser.xPositionProperty());
        line.startYProperty().bind(rayonLaser.yPositionProperty());
        line.endXProperty().bind(rayonLaser.xPointAProperty());
        line.endYProperty().bind(rayonLaser.yPointAProperty());
        this.panneauJeu.getChildren().add(line);
    }

    public void supprimerRayonLaser(RayonLaser rayonLaser){
        this.panneauJeu.getChildren().remove(this.panneauJeu.lookup("#"+ rayonLaser.getId()));
    }
}


