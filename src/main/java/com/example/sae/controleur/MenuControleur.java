package com.example.sae.controleur;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class MenuControleur {

    @FXML
    private TextField prenom;
    protected static String nom;

    @FXML
    private void demarrerPartie(ActionEvent event) throws IOException {
        nom = prenom.getText();
        System.out.println(nom);
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL resource = getClass().getResource("/com/example/sae/vueChoix.fxml");
        Parent root = fxmlLoader.load(resource);
        Scene scene = new Scene(root, 1024, 880);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Alien Survival");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    @FXML
    private void quitter(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
