package com.example.sae;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Main extends Application {

    protected static Clip clipFond;
    private static Clip clipVictoire;
    private static Clip clipDefaite;

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
        PlayMusicFond(s);
        launch();
    }
    public static void PlayMusicFond(String location) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(location));
        DataLine.Info info = new DataLine.Info(Clip.class, audioInputStream.getFormat());
        clipFond = (Clip) AudioSystem.getLine(info);
        clipFond.open(audioInputStream);
        clipFond.start();
    }

    public static boolean verifSon() {
        if(!clipFond.isRunning()){
            return false;
        }
        return true;
    }

    public static void stopMusicFond() {
        if (clipFond != null && clipFond.isRunning()) {
            clipFond.stop();
            clipFond.close();
        }
    }

    public static void PlayMusicVictoire(String location) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(location));
        DataLine.Info info = new DataLine.Info(Clip.class, audioInputStream.getFormat());
        clipVictoire = (Clip) AudioSystem.getLine(info);
        clipVictoire.open(audioInputStream);
        clipVictoire.start();
    }
    public static void stopMusicVictoire() {
        if (clipVictoire != null && clipVictoire.isRunning()) {
            clipVictoire.stop();
            clipVictoire.close();
        }
    }


    public static void PlayMusicDefaite(String location) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(location));
        DataLine.Info info = new DataLine.Info(Clip.class, audioInputStream.getFormat());
        clipDefaite = (Clip) AudioSystem.getLine(info);
        clipDefaite.open(audioInputStream);
        clipDefaite.start();
    }
    public static void stopMusicDefaite() {
        if (clipDefaite != null && clipDefaite.isRunning()) {
            clipDefaite.stop();
            clipDefaite.close();
        }
    }
}