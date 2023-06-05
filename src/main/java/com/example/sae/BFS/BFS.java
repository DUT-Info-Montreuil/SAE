package com.example.sae.BFS;

import com.example.sae.modele.Environnement;
import java.util.*;

public class BFS {
    private Environnement env;
    private Sommet source;
    /**
     * Liste des sommets de la composante connexe de g obtenue par un parcours en largeur depuis le sommet source
     */
    private ArrayList<Sommet> parcours;
    /**
     * Chaque sommet (clé) est associé à son prédécesseur (valeur) du parcours en largeur
     */
    private Map<Sommet, Sommet> predecesseurs;

    public BFS(Environnement env, Sommet source) {
        this.env = env;
        this.source = source;
        parcours = new ArrayList<>();
        predecesseurs = new HashMap<Sommet,Sommet>();
        algoBFS();
    }

    private void algoBFS() {
        LinkedList<Sommet> fifo = new LinkedList<>();
        LinkedList<Sommet> Marquage = new LinkedList<>();
        fifo.add(source); // Ajouter le sommet source à la file d'attente
        Marquage.addFirst(source); // Marquer le sommet source predecesseurs-put (source, nULL); // Le prédécesseur du sommet source est null
        predecesseurs.put(source, null);
        while (!fifo.isEmpty()) {
            Sommet suivant = fifo.pollLast();
            parcours.add(suivant);
            for (Sommet t : env.adjacents(suivant)) {
                if (!(Marquage.contains(t))) {
                    Marquage.addFirst(t);
                    fifo.addFirst(t);
                    predecesseurs.put(t, suivant);
                }
            }
        }
    }


    public ArrayList<Sommet> cheminVersSource(Sommet cible) {
        ArrayList<Sommet> chemin = new ArrayList<>();
        Sommet courant = cible;
        while (courant != null) {
            chemin.add(courant);
            courant = predecesseurs.get(courant);
        }
        Collections.reverse(chemin);
        System.out.println("Le chemin vers le sommet cible:");
        for (Sommet s : chemin) {
            System.out.println(""+ s);
        }
        return chemin;
    }


}
