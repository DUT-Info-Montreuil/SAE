package com.example.sae.CSV;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class LecteurCSV {
    private String cheminFichier = "score.csv";
    public LecteurCSV(){
    }

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

    public String lecteurFichier() {
        StringBuilder contenuFichier = new StringBuilder();

        try (Scanner sreader = new Scanner(new FileReader(cheminFichier))) {
            while (sreader.hasNextLine()) {
                String line = sreader.nextLine();
                String[] rowData = line.split(";");
                String rowDataWithTabs = String.join("\t\t", rowData);
                contenuFichier.append(rowDataWithTabs).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contenuFichier.toString();
    }
}
