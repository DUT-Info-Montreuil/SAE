package com.example.sae.controleur;

import com.example.sae.CSV.LecteurCSV;
import com.example.sae.Main;
import com.example.sae.modele.*;
import com.example.sae.vue.TerrainVue;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
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
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controleur implements Initializable {
    @FXML
    private Button boutonVague;
    @FXML
    private Pane panneauDeJeu;
    @FXML
    private TilePane tuile;
    @FXML
    private RadioButton tour1Bouton;
    @FXML
    private RadioButton tour2Bouton;
    @FXML
    private RadioButton tour3Bouton;
    @FXML
    private TextField vieStationText;
    @FXML
    private TextField argentStationText;
    @FXML
    private TextField compteurVagueText;
    @FXML
    private TextField nombreEnnemiText;
    @FXML
    private Label labelChronometre;

    private Timeline gameLoop;
    private Terrain terrain;
    private Environnement env;
    private int chronometre = 0;
    private Timeline chronometreTimeline;
    private boolean switchPause = false;
    private LecteurCSV lecteurCSV;
    private String formatTemps;

    @FXML
    void boutonAbandonner() {
        finPerdu();
    }

    private String formatChronometre(int seconds) {
        int heures = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secondes = seconds % 60;

        return String.format("%02d:%02d:%02d", heures, minutes, secondes);
    }

    @FXML
     void boutonVague(){
        env.lancementVague();
        if (chronometreTimeline == null) { // Vérifier si le chronomètre est déjà en cours d'exécution
            initChrono();
            chronometreTimeline.play();
        }
    }

    @FXML
    void boutonPause() {
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

    void appuyerTuile(double x, double y) {
        Vaisseau vaisseau = env.vaisseauPresent((int) x, (int) y);
        if(vaisseau != null){
            env.suppVaisseauPlacee(vaisseau);
        } else {
            int newX = (int) (x / 32) * 32;
            int newY = (int) (y / 32) * 32;
            if (tour1Bouton.isSelected()) {
                vaisseau = new VaisseauCourt(newX, newY, terrain, env);
                env.verifVaisseauCondition(vaisseau);
            } else {
                if (tour2Bouton.isSelected()) {
                    vaisseau = new VaisseauMoyen(newX, newY, terrain, env);
                    env.verifVaisseauCondition(vaisseau);
                } else {
                    if (tour3Bouton.isSelected()) {
                        vaisseau = new VaisseauLong(newX, newY, terrain, env);
                        env.verifVaisseauCondition(vaisseau);
                    }
                }
            }
        }
        System.out.println("clic sur bouton ajouter");
    }

        @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        terrain = new Terrain(ChoixControleur.choixTerrain);
        TerrainVue tv = new TerrainVue(terrain, tuile);
        tv.afficherTerrain(ChoixControleur.choixTerrain);

        env = new Environnement(terrain);
        lecteurCSV = new LecteurCSV();

        compteurVagueText.setText(String.valueOf(env.getCompteurVague()));
        vieStationText.setText(String.valueOf(env.getVieStation()));
        argentStationText.setText(String.valueOf(env.getArgent()));


        ListChangeListener<Ennemi> listenE = new ListObsEnnemis(panneauDeJeu);
        env.getEnnemis().addListener(listenE);

        ListChangeListener<Vaisseau> listenV = new ListObsVaisseaux(panneauDeJeu);
        env.getVaisseaux().addListener(listenV);

        ListChangeListener<BarreDeVie> listenB = new ListObsBarreDeVie(panneauDeJeu);
        env.getBarreDeVies().addListener(listenB);

        ListChangeListener<RayonLaser> listenR = new ListObsRayonLaser(panneauDeJeu);
        env.getRayonLasers().addListener(listenR);

        env.compteurVagueProperty().addListener(
                (obs, old, nouv) ->
                        compteurVagueText.setText(nouv.toString()));

        env.argentProperty().addListener(
                (obs, old, nouv) ->
                        argentStationText.setText(nouv.toString()));

        env.nombreEnnemisProperty().addListener(
                (obs, old, nouv) -> {
                        nombreEnnemiText.setText(nouv.toString());
                        if (env.getCompteurVague()==1 && env.getEnnemis().isEmpty() && env.getEnnemisVagues().isEmpty()){
                            finVictoire();
                        }
                });

        env.vieProperty().addListener(
                (obs, old, nouv) -> {
                    vieStationText.setText(nouv.toString());
                    if (env.getVieStation() == 0 ) {
                        finPerdu();
                    }
                });

        panneauDeJeu.setOnMouseClicked(event -> {
            appuyerTuile(event.getX(), event.getY());
                });

        initAnimation();
        gameLoop.play();
    }

    private void finPerdu(){
        String[] infoPartie = {MenuControleur.pseudo, String.valueOf(env.getCompteurVague()), formatTemps, "Défaite"};
        lecteurCSV.ecritureFichier(infoPartie);
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

        // Lancer la musique de defaite
        URL urlImageVaiL = Main.class.getResource("sonPerdu.wav");
        String s = urlImageVaiL.getPath();
        Main.PlayMusicDefaite(s);
    }

    private void finVictoire() {
        String[] infoPartie = {MenuControleur.pseudo, String.valueOf(env.getCompteurVague()), formatTemps, "Victoire"};
        lecteurCSV.ecritureFichier(infoPartie);
        System.out.println("vous avez gagné");
        gameLoop.stop();
        Stage primaryStage = (Stage) ((Node) boutonVague).getScene().getWindow();
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
        URL urlImageVaiL = Main.class.getResource("sonVictoire.wav");
        String s = urlImageVaiL.getPath();
        Main.PlayMusicVictoire(s);
    }

    private void initChrono(){
        // Démarrer le chronomètre
        chronometreTimeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            chronometre++;
            formatTemps = formatChronometre(chronometre);
            labelChronometre.setText(formatTemps);
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
                    if(Main.verifSonActif()==false){
                        URL urlImageVaiL = Main.class.getResource("sonFond.wav");
                        String s = urlImageVaiL.getPath();
                        Main.PlayMusicFond(s);
                        }})
        );
        gameLoop.getKeyFrames().add(kf);
    }

}