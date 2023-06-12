package com.example.sae.controleur;

import com.example.sae.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URL;

public class PerduControleur {

    @FXML
    private Button retourMenu;
    @FXML
    private Button quitter;
//sdfs

    @FXML
    public void retourMenu (ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL resource = getClass().getResource("/com/example/sae/vueMenu.fxml");
        Parent root = fxmlLoader.load(resource);
        Scene scene = new Scene(root, 1024, 880);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Alien Survival : La Dernière Lueur d'Espoir");
        primaryStage.setScene(scene);
        primaryStage.show();

        Main.stopMusicDefaite();
        try {
            Main.PlayMusicFond("/home/etudiants/info/aboukebeche/SAE/src/main/resources/com/example/sae/sonFond.wav");
//            Main.PlayMusicFond("/home/etudiants/info/sirhbira/SAE/src/main/resources/com/example/sae/sonFond.wav");
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void quitter(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}


