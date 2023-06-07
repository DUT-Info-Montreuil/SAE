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
        barreDeVie.setStyle("-fx-accent: green;");
        this.panneauJeu.getChildren().add(barreDeVie);
        System.out.println("caca = " +barre.getVieTotale());
        barreDeVie.translateXProperty().bind(barre.xProperty());
        barreDeVie.translateYProperty().bind(barre.yProperty());
        barreDeVie.progressProperty().bind(barre.vieTotaleProperty());
    }
}


