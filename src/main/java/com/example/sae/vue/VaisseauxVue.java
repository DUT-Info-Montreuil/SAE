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

import java.net.URL;
import java.util.ArrayList;

public class VaisseauxVue {
    private Image imageVaiC ;
    private Image imageVaiM ;
    private Image imageVaiL ;
    private Pane panneauJeu;
    private ImageView iv2;
    private static int compteur=0;
    private ArrayList<ProgressBar> barredeVies;


    public VaisseauxVue (Pane panneauJeu) {
        this.barredeVies = new ArrayList<>();
        this.panneauJeu = panneauJeu;
        URL urlImageVaiC = Main.class.getResource("vaisseauCourt.png");
        imageVaiC = new Image(String.valueOf(urlImageVaiC));
        URL urlImageVaiM = Main.class.getResource("vaisseauMoyen.png");
        imageVaiM = new Image(String.valueOf(urlImageVaiM));
        URL urlImageVaiL = Main.class.getResource("vaisseauLong.png");
        imageVaiL = new Image(String.valueOf(urlImageVaiL));


    }

    public void créerSprite(Vaisseau vaisseau) {
        if (vaisseau instanceof VaisseauCourt){
            iv2 = new ImageView(imageVaiC);
            iv2.setId(vaisseau.getId());

        } else {
            if (vaisseau instanceof VaisseauMoyen){
                iv2 = new ImageView(imageVaiM);
                iv2.setId(vaisseau.getId());

            } else {
                iv2 = new ImageView(imageVaiL);
                iv2.setId(vaisseau.getId());

            }
        }
            iv2.setTranslateX(vaisseau.getX());
            iv2.setTranslateY(vaisseau.getY());
            iv2.translateXProperty().bind(vaisseau.xProperty());
            iv2.translateYProperty().bind(vaisseau.yProperty());
            this.panneauJeu.getChildren().add(iv2);
            afficherRayonPortee(vaisseau);
            afficherBarreVie(vaisseau);
    }

    public void afficherBarreVie(Vaisseau vaisseau){
        ProgressBar barreDeVie = new ProgressBar();

        double centerX = vaisseau.getX();
        double centerY = vaisseau.getY() - 5;
        barreDeVie.setId(vaisseau.getId());
        barreDeVie.setProgress(vaisseau.getVieMax()); // Ajustez la valeur de progression de la barre de vie
        barreDeVie.setTranslateX(centerX);
        barreDeVie.setTranslateY(centerY);
        barreDeVie.setMaxHeight(10);
        barreDeVie.setMaxWidth(18);
        barredeVies.add(barreDeVie);
        this.panneauJeu.getChildren().add(barreDeVie);
    }
    public void mettreAJourBarreDeVie(Vaisseau vaisseau) {
        if(!this.barredeVies.isEmpty()){
        double pourcentageVie = vaisseau.getVie() / vaisseau.getVieMax();
        for (int i = 0; i < barredeVies.size(); i++){
            ProgressBar barreDeVie = barredeVies.get(i);
            if(barreDeVie.getId() == vaisseau.getId()) {
                barreDeVie.setProgress(pourcentageVie);
                }
            }
        }
    }
    public void afficherRayonPortee(Vaisseau vaisseau) {
        Circle rayonPortee = new Circle();

        double tourelleLargeur = vaisseau.getPortee();
        double tourelleHauteur = vaisseau.getPortee();

        double centerX = vaisseau.getX() + tourelleLargeur / 32;
        double centerY = vaisseau.getY() + tourelleHauteur / 32;

        rayonPortee.setCenterX(centerX);
        rayonPortee.setCenterY(centerY);
        rayonPortee.setRadius(vaisseau.getPortee());
        rayonPortee.setFill(null); // Aucun remplissage
        rayonPortee.setStroke(Color.rgb(255, 177, 6));
        rayonPortee.setStrokeWidth(2.0);
        rayonPortee.setId(vaisseau.getId());

        DropShadow dropShadow = new DropShadow(10, Color.rgb(255, 0, 2, 0.4));
        rayonPortee.setEffect(dropShadow);

        panneauJeu.getChildren().add(rayonPortee);
    }

    public void supprimerSprite(Vaisseau vaisseau){
        this.panneauJeu.getChildren().remove(this.panneauJeu.lookup("#"+ vaisseau.getId()));
        this.panneauJeu.getChildren().remove(this.panneauJeu.lookup("#"+ vaisseau.getId()));
        this.panneauJeu.getChildren().remove(this.panneauJeu.lookup("#"+ vaisseau.getId()));
    }
}


