package com.example.sae;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javax.print.attribute.standard.Media;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Main extends Application {

    private static Clip clip;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("vueMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1024, 880);
        stage.setTitle("Alien Survival");
        stage.setScene(scene);

        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        String s = "/home/etudiants/info/sirhbira/SAE/src/main/resources/com/example/sae/sonFond.wav";
        PlayMusic(s);
        launch();
    }
    public static void PlayMusic(String location) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(location));
        DataLine.Info info = new DataLine.Info(Clip.class, audioInputStream.getFormat());
        Clip clip = (Clip) AudioSystem.getLine(info);
        clip.open(audioInputStream);
        clip.start();
    }

    public static void StopMusic() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }


}