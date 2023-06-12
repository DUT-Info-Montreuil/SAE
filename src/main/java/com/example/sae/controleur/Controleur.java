package com.example.sae.controleur;


import com.example.sae.Main;
import com.example.sae.modele.*;
import com.example.sae.vue.TerrainVue;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controleur implements Initializable {
    @FXML
    private Button boutonVague;
    @FXML
    private Pane PaneauDeJeu;
    @FXML
    private TilePane tilePane;
    @FXML
    private RadioButton tour1;
    @FXML
    private RadioButton tour2;
    @FXML
    private RadioButton tour3;
    @FXML
    private TextField vieStation;
    @FXML
    private TextField argentStation;
    @FXML
    private TextField compteurV;
    @FXML
    private TextField tailleEnnemi;
    @FXML
    private Button boutonPause;
    @FXML
    private Label labelChronometre;
    private Timeline gameLoop;
    private Terrain terrain;
    private Environnement env;
    private int chronometre = 0;
    private Timeline chronometreTimeline;
    boolean switchPause = false;

    @FXML
    void boutonAbandonner(ActionEvent event) {
        finPerdu();
    }

    private String formatChronometre(int seconds) {
        int heures = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secondes = seconds % 60;

        return String.format("%02d:%02d:%02d", heures, minutes, secondes);
    }


    @FXML
     void boutonVague(ActionEvent event){
        env.lancementVague();
        if (chronometreTimeline == null) { // Vérifier si le chronomètre est déjà en cours d'exécution
            initChrono();
            chronometreTimeline.play();
        }
    }

    @FXML
    void boutonPause(ActionEvent event) {
        if (!switchPause){
            gameLoop.pause();
            chronometreTimeline.pause();
            switchPause = true;
        } else {
            gameLoop.play();
            chronometreTimeline.play();
            switchPause = false;
        }
    }

    void appuyer(ActionEvent event, double x, double y) {
        Vaisseau vaisseau = env.vaisseauPresent((int) x, (int) y);
        if(vaisseau != null){
            env.suppVaisseauPlacee(vaisseau);
        } else {
            if (tour1.isSelected()) {
                vaisseau = new VaisseauCourt((int) (x / 32) * 32, (int) (y / 32) * 32, terrain, env);
                env.verifVaisseau(vaisseau);
            } else {
                if (tour2.isSelected()) {
                    vaisseau = new VaisseauMoyen((int) (x / 32) * 32, (int) (y / 32) * 32, terrain, env);
                    env.verifVaisseau(vaisseau);
                } else {
                    if (tour3.isSelected()) {
                        vaisseau = new VaisseauLong((int) (x / 32) * 32, (int) (y / 32) * 32, terrain, env);
                        env.verifVaisseau(vaisseau);
                    }
                }
            }
        }
        System.out.println("clic sur bouton ajouter");
    }

        @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        terrain = new Terrain();
        TerrainVue tv = new TerrainVue(terrain, tilePane);
        tv.afficherTerrain();

        env = new Environnement(terrain);

        compteurV.setText(String.valueOf(env.getCompteurVague()));
        vieStation.setText(String.valueOf(env.getVieStation()));
        argentStation.setText(String.valueOf(env.getArgent()));


        ListChangeListener<Ennemi> listenE = new ListObsEnnemis(PaneauDeJeu);
        env.getEnnemi().addListener(listenE);

        ListChangeListener<Vaisseau> listenV = new ListObsVaisseaux(PaneauDeJeu);
        env.getVaisseaux().addListener(listenV);

        ListChangeListener<BarreDeVie> listenB = new ListObsBarreDeVie(PaneauDeJeu);
        env.getBarreDeVie().addListener(listenB);

        env.compteurVagueProperty().addListener(
                (obs, old, nouv) -> {
                        compteurV.setText(nouv.toString());
                        if (env.getCompteurVague()==11 && env.getEnnemi().isEmpty() && env.getEnnemisVagues().isEmpty()){
                                finVictoire();
                        }
                });

        env.argentProperty().addListener(
                (obs, old, nouv) ->
                        argentStation.setText(nouv.toString()));

        env.nbEnnemisProperty().addListener(
                (obs, old, nouv) ->
                        tailleEnnemi.setText(nouv.toString()));

        env.vieProperty().addListener(
                (obs, old, nouv) -> {
                    vieStation.setText(nouv.toString());
                    if (env.getVieStation() == 0 ) {
                        finPerdu();
                    }
                });

        PaneauDeJeu.setOnMouseClicked( event -> {
            appuyer(null, event.getX(), event.getY());
                });

        initAnimation();
        gameLoop.play();
    }

    private void finPerdu(){
        System.out.println("Vous avez perdu");
        gameLoop.stop();
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL resource = getClass().getResource("/com/example/sae/vuePerdu.fxml");
        Parent root = null;
        try {
            root = fxmlLoader.load(resource);
        } catch (IOException e) {
        }
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) ((Node) boutonVague).getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.show();
        // Arrêter la musique en cours (si elle est en cours de lecture)
        Main.stopMusicFond();

        // Lancer la musique de victoire
        try {
//            Main.PlayMusicDefaite("/home/etudiants/info/sirhbira/SAE/src/main/resources/com/example/sae/sonPerdu.wav");
            Main.PlayMusicDefaite("/home/etudiants/info/aboukebeche/SAE/src/main/resources/com/example/sae/sonPerdu.wav");
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    private void finVictoire() {
        System.out.println("vous avez gagné");
        gameLoop.stop();
        Stage primaryStage = (Stage) ((Node) (Node) boutonVague).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL resource = getClass().getResource("/com/example/sae/vueFin.fxml");
        Parent root = null;
        try {
            root = fxmlLoader.load(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root, 1024, 880);
        primaryStage.setResizable(false);
        primaryStage.setTitle("ALien Survival : La Dernière Lueur d'Espoir");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Arrêter la musique en cours (si elle est en cours de lecture)
        Main.stopMusicFond();

        // Lancer la musique de victoire
        try {
            Main.PlayMusicVictoire("/home/etudiants/info/aboukebeche/SAE/src/main/resources/com/example/sae/sonVictoire.wav");
//            Main.PlayMusicVictoire("/home/etudiants/info/sirhbira/SAE/src/main/resources/com/example/sae/sonVictoire.wav");
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    private void initChrono(){
        // Démarrer le chronomètre
        chronometreTimeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            chronometre++;
            String formattedTime = formatChronometre(chronometre);
            labelChronometre.setText(formattedTime);
        }));
        chronometreTimeline.setCycleCount(Timeline.INDEFINITE);
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
                    if(Main.verifSon()==false){
                        try {
                            Main.PlayMusicFond("/home/etudiants/info/aboukebeche/SAE/src/main/resources/com/example/sae/sonFond.wav");
//                            Main.PlayMusicFond("/home/etudiants/info/sirhbira/SAE/src/main/resources/com/example/sae/sonFond.wav");
                        } catch (UnsupportedAudioFileException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (LineUnavailableException e) {
                            e.printStackTrace();
                        }
                    }
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }

}