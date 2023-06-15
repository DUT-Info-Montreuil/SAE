package com.example.sae.CSV;

import java.io.*;
import java.util.Scanner;

public class LecteurCSV {
    private String[][] tableauScore = {{"Nom", "Vague", "Temps", "V/D"}};
    private String cheminFichier = "score.csv";
    public LecteurCSV(){}
/*
    public static void main(String[] args) {
        String[][] tableauScore = new String[][]{
                {"Nom", "Vague", "Temps", "Victoire"},
                {"Nom", "Vague", "Temps", "Victoire"},
                {"Nom", "Vague", "Temps", "Victoire"}};
        LecteurCSV csv = new LecteurCSV();
        for (String[] t : tableauScore){
            csv.ecritureFichier(t);
        }
        csv.lecteurFichier();
    }
*/
    public void ecritureFichier(String[] tableau) {
        try (FileWriter writer = new FileWriter(cheminFichier, true)) {
            for (int i = 0; i < tableau.length; i++) {
                writer.append(tableau[i]);
                if (i != tableau.length - 1) {
                    writer.append(";");
                }
            }
            writer.append("\r\n");
            writer.close();
            System.out.println("Écriture dans le fichier CSV terminée avec succès.");
        } catch (IOException e) {
            System.out.println("Erreur lors de l'écriture dans le fichier CSV : " + e.getMessage());
        }
    }
    public void lecteurFichier(){
        try (Scanner sreader = new Scanner(new FileReader(cheminFichier))) {
            String line;
            while (sreader.hasNextLine()) {
                line = sreader.nextLine();
                String[] rowData = line.split(";");
                for (String data : rowData) {
                    System.out.print(data + "\t");
                }
                System.out.println();
            }
            sreader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
