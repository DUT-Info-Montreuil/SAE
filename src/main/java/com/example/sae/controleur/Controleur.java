package com.example.sae.controleur;


import com.example.sae.Main;
import com.example.sae.modele.*;
import com.example.sae.vue.EnnemisVue;
import com.example.sae.vue.TerrainVue;
import com.example.sae.vue.VaisseauxVue;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Controleur implements Initializable {

    @FXML
    private Button boutonVague;

    @FXML
    private Pane PaneauDeJeu;

    @FXML
    private TilePane tilePane;

    private Timeline gameLoop;

     private Terrain terrain;

     private VaisseauxVue vaisseauxVue;

     private Vaisseau vaisseau;

     private Environnement env;

     @FXML
     private RadioButton tour1;

    @FXML
    private TextField vieStation;

    private int i = 0;

    @FXML
    private Button boutonPause;

    @FXML
    void boutonPause(ActionEvent event) {
        if (i == 0){
            gameLoop.pause();
            i = 1;
        } else {
            gameLoop.play();
            i = 0;
        }
    }

    void ajouter(ActionEvent event, double x, double y) {
        if (tour1.isSelected()) {
            vaisseau = new VaisseauLong((int) (x/16)*16, (int) (y/16)*16, terrain, env);
            if (vaisseau.vaisseauBienPlacee()) {
                vaisseauxVue = new VaisseauxVue(PaneauDeJeu, vaisseau);
                vaisseauxVue.créerSprite(vaisseau);
                env.ajouterVaisseau(vaisseau);
                 System.out.println("Tourelle ajoutée");
        } else {
            System.out.println("Erreur ajout");
        }
//        } else if (Loup.isSelected()) {
//            for (int i = 0; i < nbIndividus; i++) {
//                Loup loup = new Loup(environnement);
//                environnement.ajouter(loup);
//                Circle spriteLoup = creerSprite(loup);
//                PaneauDeJeu.getChildren().add(spriteLoup); // Ajouter le cercle au panneau de jeu
//            }
        }
        System.out.println("clic sur bouton ajouter");

    }

    @FXML
//     void boutonVague(ActionEvent event) {
//        if (boutonVague.isPressed()) {
//            ennemi = new Ennemi(4, terrain, 100);
//            ImageView iv2 = new ImageView(imageEnn);
//
//            iv2.translateXProperty().bind(ennemi.xProperty());
//            iv2.translateYProperty().bind(ennemi.yProperty());
//
//            // Ajoutez iv2 à PaneauDeJeu ou à un autre conteneur approprié
//            PaneauDeJeu.getChildren().add(iv2);
//        }
//    }

        @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        terrain = new Terrain();
        TerrainVue tv = new TerrainVue(terrain, tilePane);
        tv.afficherTerrain();

        env = new Environnement(terrain);

        ListChangeListener<Ennemi> listen = new ListObs(PaneauDeJeu);
        env.getEnnemi().addListener(listen);

        env.vieProperty().addListener(
                    (obs, old, nouv) ->
                            vieStation.setText(nouv.toString()));
        initAnimation();
        gameLoop.play();

        PaneauDeJeu.setOnMouseClicked( event -> {
            ajouter(null, event.getX(), event.getY());
                });

    }


    private void initAnimation() {
        gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                // on définit le FPS (nbre de frame par seconde)
                Duration.seconds(0.1),
                // on définit ce qui se passe à chaque frame
                // c'est un eventHandler d'ou le lambda
                (ev ->{
                    env.unTour();
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }

}