package com.example.sae.controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;

public class ChoixControleur {
    public static int choixTerrain;

    // quand le bouton est cliqué lancement du jeu

    @FXML
    private void terrain1(ActionEvent event) throws IOException {
        choixTerrain = 1;
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL resource = getClass().getResource("/com/example/sae/vue.fxml");
        Parent root = fxmlLoader.load(resource);
        Scene scene = new Scene(root, 1024, 880);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Alien Survival");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private void terrain2(ActionEvent event) throws IOException {
        choixTerrain = 2;
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL resource = getClass().getResource("/com/example/sae/vue.fxml");
        Parent root = fxmlLoader.load(resource);
        Scene scene = new Scene(root, 1024, 880);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Alien Survival");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
