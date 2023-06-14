package com.example.sae.CSV;

import com.example.sae.Main;

import java.io.*;
import java.net.URL;

public class LecteurCSV {
    private String[][] tableauScore;
    private String cheminFichier;
    public LecteurCSV(){
        tableauScore = new String[][]{
                {"Nom", "Vague", "Temps", "Victoire"}};
        URL urlImageVaiL = Main.class.getResource("score.csv");
        cheminFichier = urlImageVaiL.getPath();
    }

    public void ecritureFichier(String[] tableau) {
        try (FileWriter writer = new FileWriter(cheminFichier)) {
            for (int i = 0; i < tableau.length; i++) {
                writer.append(tableau[i]);
                if (i != tableau.length - 1) {
                    writer.append(",");
                }
            }
            writer.append("\r\n");
            System.out.println("Écriture dans le fichier CSV terminée avec succès.");
        } catch (IOException e) {
            System.out.println("Erreur lors de l'écriture dans le fichier CSV : " + e.getMessage());
        }
    }
    public void lecteurFichier(){
        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] rowData = line.split(",");
                for (String data : rowData) {
                    System.out.print(data + "\t");
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
