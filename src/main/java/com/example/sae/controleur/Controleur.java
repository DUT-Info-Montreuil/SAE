package com.example.sae.controleur;


import com.example.sae.modele.*;
import com.example.sae.vue.TerrainVue;
import com.example.sae.vue.VaisseauxVue;
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
import javafx.scene.image.ImageView;
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
    private Pane PaneauDeJeu;

    @FXML
    private TilePane tilePane;

    private Timeline gameLoop;

     private Terrain terrain;

     private Environnement env;

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

    private int i = 0;

    @FXML
    private Button boutonPause;

    @FXML
    private Label labelChronometre;

    private FinControleur finControleur;

    private int chronometre = 0;


    private Timeline chronometreTimeline;

    @FXML
    void boutonAbandonner(ActionEvent event) {
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
    }

    private String formatChronometre(int seconds) {
        int heures = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secondes = seconds % 60;

        return String.format("%02d:%02d:%02d", heures, minutes, secondes);
    }


    @FXML
     void boutonVague(ActionEvent event) throws IOException {
        if (env.getCompteurVague() < 10) {
            if (env.getEnnemi().isEmpty()) {
                if (env.getEnnemisVagues().isEmpty()) {
                    env.setCompteurVague();
                    // Démarrer le chronomètre
                     chronometreTimeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
                        chronometre++;
                        String formattedTime = formatChronometre(chronometre);
                        labelChronometre.setText(formattedTime);
                    }));
                    chronometreTimeline.setCycleCount(Timeline.INDEFINITE);
                    chronometreTimeline.play();

                    env.lancerVague();
                }
            }
        } else {
            System.out.println("vous avez gagné");
            gameLoop.stop();
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader();
            URL resource = getClass().getResource("/com/example/sae/vueFin.fxml");
            Parent root = fxmlLoader.load(resource);
            Scene scene = new Scene(root, 1024, 880);
            primaryStage.setResizable(false);
            primaryStage.setTitle("ALien Survival : La Dernière Lueur d'Espoir");
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }

    @FXML
    void boutonPause(ActionEvent event) {
        if (i == 0){
            gameLoop.pause();
            chronometreTimeline.pause();
            i = 1;
        } else {
            gameLoop.play();
            chronometreTimeline.play();

            i = 0;
        }
    }

    public void ajouterVaisseau(Vaisseau vaisseau){
        if ((env.getArgent()-vaisseau.getPrix()) < 0){
            System.out.println("Pas assez d'argent");
        }else {
            if (vaisseau.vaisseauBienPlacee()) {
                env.ajouterVaisseau(vaisseau);
                env.suppArgent(vaisseau);
                System.out.println("Tourelle ajoutée");
            } else {
                System.out.println("Erreur ajout");
            }
        }
    }

    void appuyer(ActionEvent event, double x, double y) {
        Vaisseau vaisseau = env.vaisseauPresent((int) x, (int) y);
        if(vaisseau != null){
            if (vaisseau.getVie() >= vaisseau.getVieMax()/2){
                env.getVaisseaux().remove(vaisseau);
            }
        } else {
            if (tour1.isSelected()) {
                vaisseau = new VaisseauCourt((int) (x / 32) * 32, (int) (y / 32) * 32, terrain, env);
                ajouterVaisseau(vaisseau);
            } else {
                if (tour2.isSelected()) {
                    vaisseau = new VaisseauMoyen((int) (x / 32) * 32, (int) (y / 32) * 32, terrain, env);
                    ajouterVaisseau(vaisseau);
                } else {
                    if (tour3.isSelected()) {
                        vaisseau = new VaisseauLong((int) (x / 32) * 32, (int) (y / 32) * 32, terrain, env);
                        ajouterVaisseau(vaisseau);
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

        ListChangeListener<Ennemi> listenE = new ListObsEnnemis(PaneauDeJeu);
        env.getEnnemi().addListener(listenE);

        ListChangeListener<Vaisseau> listenV = new ListObsVaisseaux(PaneauDeJeu);
        env.getVaisseaux().addListener(listenV);

        ListChangeListener<BarreDeVie> listenB = new ListObsBarreDeVie(PaneauDeJeu);
        env.getBarreDeVie().addListener(listenB);

        env.compteurVagueProperty().addListener(
                (obs, old, nouv) ->
                        compteurV.setText(nouv.toString()));


        env.argentProperty().addListener(
                (obs, old, nouv) ->
                        argentStation.setText(nouv.toString()));

        env.nbEnnemisProperty().addListener(
                (obs, old, nouv) ->
                        tailleEnnemi.setText(nouv.toString()));

        env.vieProperty().addListener(
                (obs, old, nouv) -> {
                    vieStation.setText(nouv.toString());
                    if (env.getVieStation() == 0 ){
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
                    }
                });

        initAnimation();
        gameLoop.play();

        PaneauDeJeu.setOnMouseClicked( event -> {
            appuyer(null, event.getX(), event.getY());
                });
        compteurV.setText(String.valueOf(env.getCompteurVague()));
        vieStation.setText(String.valueOf(env.getVieStation()));
        argentStation.setText(String.valueOf(env.getArgent()));

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