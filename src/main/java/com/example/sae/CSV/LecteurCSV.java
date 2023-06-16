package com.example.sae.CSV;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
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

//    public  void relierLecteurTableau(TableView<String[]> tableView, TableColumn<String[], String> nomColonne, TableColumn<String[], String> vagueColonne, TableColumn<String[], String> tempsColonne, TableColumn<String[], String> vdColonne) {
  //      tableView.getColumns().addAll(nomColonne, vagueColonne, tempsColonne, vdColonne);
    //    tableView.getItems().clear();
      //  lecteurFichier(tableView);
    //}

    public void lecteurFichier(TableView<String[]> tableView) {
        try (Scanner sreader = new Scanner(new FileReader(cheminFichier))) {
            ArrayList<String[]> rowDatas = new ArrayList<>();
            while (sreader.hasNextLine()) {
                String[] rowData = sreader.nextLine().split(";");
                rowDatas.add(rowData);
            }
            tableView.getItems().addAll(rowDatas);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String lecteurFichier() {
        StringBuilder contenuFichier = new StringBuilder();

        try (Scanner sreader = new Scanner(new FileReader(cheminFichier))) {
            while (sreader.hasNextLine()) {
                String line = sreader.nextLine();
                contenuFichier.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contenuFichier.toString();
    }
}
