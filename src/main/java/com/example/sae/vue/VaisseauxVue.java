package com.example.sae.vue;

import com.example.sae.Main;
import com.example.sae.modele.Vaisseau;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;

public class VaisseauxVue {
    private Image imageVai ;
    private Pane panneauJeu;

    public VaisseauxVue (Pane panneauJeu) {
        this.panneauJeu = panneauJeu;
        URL urlImageVai = Main.class.getResource("vaiseauNormal.png");
        imageVai = new Image(String.valueOf(urlImageVai));
    }

    public void créerSprite(Vaisseau personnage) {
        ImageView iv5 = new ImageView(imageVai);
        iv5.translateXProperty().bind(personnage.xProperty());
        iv5.translateYProperty().bind(personnage.yProperty());
        this.panneauJeu.getChildren().add(iv5);

    }

}


