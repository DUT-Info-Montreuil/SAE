package com.example.sae.vue;

import com.example.sae.Main;
import com.example.sae.modele.Terrain;
import com.example.sae.modele.Ennemi;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

import java.net.URL;

public class TerrainVue {
    private TilePane tilePane;
    private Terrain terrain;


    public TerrainVue(Terrain terrain, TilePane tilePane) {
        this.terrain = terrain;
        this.tilePane = tilePane;
    }





    public void afficherTerrain() {
        int[][] codesTuiles = this.terrain.getTileMap();
        URL urlImageSol = Main.class.getResource("sol.png");
        Image imageSol = new Image(String.valueOf(urlImageSol));

        URL urlImageSol2 = Main.class.getResource("sol2.png");
        Image imageSol2 = new Image(String.valueOf(urlImageSol2));

        URL urlImageSol4 = Main.class.getResource("sol4.png");
        Image imageSol4 = new Image(String.valueOf(urlImageSol4));

        URL urlImageStation = Main.class.getResource("station.png");
        Image imageStation = new Image(String.valueOf(urlImageStation));

        URL urlImageSol5 = Main.class.getResource("sol5.png");
        Image imageSol5 = new Image(String.valueOf(urlImageSol5));

        URL urlImageSol6 = Main.class.getResource("sol6.png");
        Image imageSol6 = new Image(String.valueOf(urlImageSol6));

        URL urlImageSol7 = Main.class.getResource("sol7.png");
        Image imageSol7 = new Image(String.valueOf(urlImageSol7));

        for (int y = 0; y < codesTuiles.length; y++) {
            for (int x = 0; x < codesTuiles[y].length; x++) {
                if (codesTuiles[y][x] == 0) {
                    ImageView iv3 = new ImageView(imageSol2);
                    this.tilePane.getChildren().add(iv3);
                } else if (codesTuiles[y][x] == 1) {
                    ImageView iv1 = new ImageView(imageSol);
                    this.tilePane.getChildren().add(iv1);
                } else if (codesTuiles[y][x] == 2) {
                    ImageView iv4 = new ImageView(imageStation);
                    this.tilePane.getChildren().add(iv4);
                } else if (codesTuiles[y][x] == 5) {
                    ImageView iv5 = new ImageView(imageSol5);
                    this.tilePane.getChildren().add(iv5);
                } else if (codesTuiles[y][x] == 6) {
                    ImageView iv6 = new ImageView(imageSol6);
                    this.tilePane.getChildren().add(iv6);
                } else if (codesTuiles[y][x] == 7) {
                    ImageView iv7 = new ImageView(imageSol7);
                    this.tilePane.getChildren().add(iv7);
                } else if (codesTuiles[y][x] == 4) {
                    ImageView iv6 = new ImageView(imageSol4);
                    this.tilePane.getChildren().add(iv6);
                }
            }
        }
    }
}
