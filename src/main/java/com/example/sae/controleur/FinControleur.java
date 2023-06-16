package com.example.sae.controleur;

import com.example.sae.CSV.LecteurCSV;
import com.example.sae.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;

public class FinControleur {
    @FXML
    private static TableView<String[]> tableView;
    @FXML
    private static TableColumn<String[], String> nomColonne;
    @FXML
    private static TableColumn<String[], String> vagueColonne;
    @FXML
    private static TableColumn<String[], String> tempsColonne;
    @FXML
    private static TableColumn<String[], String> vdColonne;
    private static LecteurCSV lecteurCSV;

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
        Main.stopMusicVictoire();
        URL urlImageVaiL = Main.class.getResource("sonFond.wav");
        String s = urlImageVaiL.getPath();
        Main.PlayMusicFond(s);
    }

    @FXML
    private void quitter(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}

