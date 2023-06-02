package com.example.sae.vue;

import com.example.sae.Main;
import com.example.sae.modele.Vaisseau;
import com.example.sae.modele.VaisseauLong;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.net.URL;

public class VaisseauxVue {
    private Image imageVai ;
    private Pane panneauJeu;
    private Vaisseau vaisseau;

    public VaisseauxVue (Pane panneauJeu, Vaisseau vaisseau) {
        this.panneauJeu = panneauJeu;
        this.vaisseau = vaisseau;
        URL urlImageVai = Main.class.getResource("vaiseauNormal.png");
        imageVai = new Image(String.valueOf(urlImageVai));

    }

    public void créerSprite(Vaisseau personnage) {
            ImageView iv5 = new ImageView(imageVai);
            iv5.setTranslateX(vaisseau.getX());
            iv5.setTranslateY(vaisseau.getY());
            iv5.translateXProperty().bind(vaisseau.xProperty());
            iv5.translateYProperty().bind(vaisseau.yProperty());
            this.panneauJeu.getChildren().add(iv5);
            VaisseauxVue vaisseauxVue = new VaisseauxVue(panneauJeu, vaisseau);
            vaisseauxVue.afficherRayonPortee();
    }
    public void afficherRayonPortee() {
        Circle rayonPortee = new Circle();

        double tourelleLargeur = vaisseau.getPortee();
        double tourelleHauteur = vaisseau.getPortee();

        double centerX = vaisseau.getX() + tourelleLargeur / 16;
        double centerY = vaisseau.getY() + tourelleHauteur / 16;

        rayonPortee.setCenterX(centerX);
        rayonPortee.setCenterY(centerY);
        rayonPortee.setRadius(vaisseau.getPortee());
        rayonPortee.setFill(null); // Aucun remplissage
        rayonPortee.setStroke(Color.rgb(255, 177, 6));
        rayonPortee.setStrokeWidth(2.0);

        DropShadow dropShadow = new DropShadow(10, Color.rgb(255, 0, 2, 0.4));
        rayonPortee.setEffect(dropShadow);

        panneauJeu.getChildren().add(rayonPortee);
    }

}


