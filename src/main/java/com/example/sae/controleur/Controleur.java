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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
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

    private Ennemi ennemi;

    private EnnemisVue ennemisVue;

     private Image imageEnn;

     private Terrain terrain;

     private Vaisseau vaisseau;

     private Environnement env;

    @FXML
    private ImageView vaisseauNormal;


    private int i = 0;

    private Image imageVai ;

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

    @FXML
     void boutonVague(ActionEvent event) {
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
    }


    @FXML
    void testTourelle(ActionEvent event) {
        vaisseau = new Vaisseau(0, 0, terrain, 30, env); // Modifier cette ligne

        creerSpriteTourelle(vaisseau);

        vaisseauNormal.setOnMouseDragged(eve -> {
            vaisseau.setX((int) eve.getSceneX());
            vaisseau.setY((int) (eve.getSceneY()));
            System.out.println("Tourelle :" + vaisseau.getX() + " " + vaisseau.getY());
        });
    }


    public void creerSpriteTourelle(Vaisseau t) {
//        Circle c = new Circle(8);
//        c.setFill(Color.RED);

        ImageView iv5 = new ImageView(imageVai);
        iv5.setTranslateX(t.getX());
        iv5.setTranslateY(t.getY());
        iv5.translateXProperty().bind(t.xProperty());
        iv5.translateYProperty().bind(t.yProperty());
        this.PaneauDeJeu.getChildren().add(iv5);

//        c.setTranslateX(t.getX());
//        c.setTranslateY(t.getY());
//        c.translateXProperty().bind(t.xProperty());
//        c.translateYProperty().bind(t.yProperty());
//        PaneauDeJeu.getChildren().add(c);
//        c.setOnMouseExited(e -> {
//                    ajouterDefenseDansModele(t.getX(), t.getY());
//                    ajusterEmplacementtourelle(t, (Math.round(t.getX() / 16)), Math.round(t.getY() / 16));
//                    afficherTerrain(env.getTerrainModele());
//                    System.out.println(t.xProperty().getValue() + " " + t.yProperty().getValue());
//
//                    if(TourellebienPlacé(t)) {
//                        env.ajouterDefense(t);
//                        System.out.println("Tourelle ajoutée");
//                    }
//                    else {
//                        System.out.println("Erreur ajout");
//                        Pa.getChildren().remove(c);
//                    }
//
//                }
//        );
        }


        @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        terrain = new Terrain(40); // initialisation du terrain avec une taille de case de 32
        TerrainVue tv = new TerrainVue(terrain, tilePane);
        tv.afficherTerrain();

//            Ennemi ennemi = new Alien(terrain, env);
//            EnnemisVue ennemisVue = new EnnemisVue(PaneauDeJeu);
//            ennemisVue.créerSprite(ennemi);



        env = new Environnement(terrain);

        ListChangeListener<Ennemi> listen = new ListObs(PaneauDeJeu);
        env.getEnnemi().addListener(listen);


        URL urlImageVai = Main.class.getResource("vaiseauNormal.png");
        imageVai = new Image(String.valueOf(urlImageVai));

        initAnimation();
        gameLoop.play();



        vaisseauNormal.setOnMouseClicked( event -> {
                testTourelle(null);
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