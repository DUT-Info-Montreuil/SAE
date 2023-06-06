package com.example.sae;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // essai
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("vue.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1024, 880);
        stage.setTitle("TowerDefense");
        stage.setScene(scene);
//        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
