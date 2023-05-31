package com.example.sae.vue;

import com.example.sae.Main;
import com.example.sae.modele.Alien;
import com.example.sae.modele.Ennemi;
import com.example.sae.modele.LimaceLente;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;

public class EnnemisVue {

    private Image imageAl;
    private Image imageLim ;
    private Image imageChe ;
    private Pane panneauJeu;
    private   ImageView iv2;

    public EnnemisVue(Pane panneauJeu) {
        this.panneauJeu = panneauJeu;
        URL urlImageEnn = Main.class.getResource("Alien.png");
        imageAl = new Image(String.valueOf(urlImageEnn));
        URL urlImageLim = Main.class.getResource("Limace.png");
        imageLim = new Image(String.valueOf(urlImageLim));
        URL urlImageChe = Main.class.getResource("Cheval.png");
        imageChe = new Image(String.valueOf(urlImageChe));
    }

    public void créerSprite(Ennemi ennemi) {
        if (ennemi instanceof Alien){
            iv2 = new ImageView(imageAl);
        } else {
            if (ennemi instanceof LimaceLente){
                iv2 = new ImageView(imageLim);
            } else {
                iv2 = new ImageView(imageChe);
            }
        }
        iv2.translateXProperty().bind(ennemi.xProperty());
        iv2.translateYProperty().bind(ennemi.yProperty());
        this.panneauJeu.getChildren().add(iv2);

    }

}
