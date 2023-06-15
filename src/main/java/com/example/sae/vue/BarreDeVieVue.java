package com.example.sae.vue;

import com.example.sae.modele.*;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;

public class BarreDeVieVue {
    private Pane panneauJeu;

    public BarreDeVieVue (Pane panneauJeu) {
        this.panneauJeu = panneauJeu;
    }

    public void afficherBarreVie(BarreDeVie barre){
        ProgressBar barreDeVie = new ProgressBar();
        barreDeVie.setId(barre.getId());
        barreDeVie.setProgress(barre.getVieTotale()); // Ajustez la valeur de progression de la barre de vie
        barreDeVie.setTranslateX(barre.getX());
        barreDeVie.setTranslateY(barre.getY());
        barreDeVie.setMaxHeight(10);
        barreDeVie.setMaxWidth(30);
        barreDeVie.setStyle(barre.getCouleurStyle()); // Utilisez la couleur appropriée en fonction de la vie
        this.panneauJeu.getChildren().add(barreDeVie);
        System.out.println(barre.getVieTotale());
        barreDeVie.translateXProperty().bind(barre.xProperty());
        barreDeVie.translateYProperty().bind(barre.yProperty());
        barreDeVie.progressProperty().bind(barre.vieTotaleProperty());
        barreDeVie.styleProperty().bind(barre.couleurStyleProperty());
    }
}


