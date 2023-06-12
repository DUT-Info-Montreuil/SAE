package com.example.sae;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

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

    public static void main(String[] args){
        URL urlImageVaiL = Main.class.getResource("sonFond.wav");
        String s = urlImageVaiL.getPath();
        PlayMusicFond(s);
        launch();
    }
    public static void PlayMusicFond(String location){
        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File(location));
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        DataLine.Info info = new DataLine.Info(Clip.class, audioInputStream.getFormat());
        try {
            clipFond = (Clip) AudioSystem.getLine(info);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        try {
            clipFond.open(audioInputStream);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

    public static void PlayMusicVictoire(String location){
        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File(location));
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        DataLine.Info info = new DataLine.Info(Clip.class, audioInputStream.getFormat());
        try {
            clipVictoire = (Clip) AudioSystem.getLine(info);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        try {
            clipVictoire.open(audioInputStream);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        clipVictoire.start();
    }
    public static void stopMusicVictoire() {
        if (clipVictoire != null && clipVictoire.isRunning()) {
            clipVictoire.stop();
            clipVictoire.close();
        }
    }


    public static void PlayMusicDefaite(String location){
        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File(location));
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        DataLine.Info info = new DataLine.Info(Clip.class, audioInputStream.getFormat());
        try {
            clipDefaite = (Clip) AudioSystem.getLine(info);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        try {
            clipDefaite.open(audioInputStream);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        clipDefaite.start();
    }
    public static void stopMusicDefaite() {
        if (clipDefaite != null && clipDefaite.isRunning()) {
            clipDefaite.stop();
            clipDefaite.close();
        }
    }
}