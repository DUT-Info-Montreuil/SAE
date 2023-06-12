package com.example.sae.modele;

import com.example.sae.BFS.BFS;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Environnement {
    private ObservableList<Ennemi> ennemis;
    private ObservableList<Vaisseau> vaisseaux;
    private ObservableList<BarreDeVie> barreDeVies;
    private IntegerProperty nbEnnemis;
    private int tours;
    private Terrain terrain;
    private Station station;
    private Vague vague;
    private Boutique boutique;
    private BFS bfs;
    private List<Point> chemin;

    public Environnement(Terrain terrain) {
        this.bfs = new BFS();
        this.ennemis = FXCollections.observableArrayList();
        this.vaisseaux = FXCollections.observableArrayList();
        this.barreDeVies = FXCollections.observableArrayList();
        this.tours = 0;
        this.terrain = terrain;
        this.station = new Station(terrain, this);
        this.boutique = new Boutique(this);
        this.vague = new Vague(terrain, this, boutique);
        this.nbEnnemis = new SimpleIntegerProperty(0);
        this.chemin = BFS.bfs(terrain.getTileMap(),new Point(0, 3), new Point(21, 31));
        for (Point tuile : chemin) {
            System.out.println(tuile);
        }
    }

    public List<Point> getChemin() {
        return chemin;
    }

    public ObservableList<Ennemi> getEnnemi() {
        return ennemis;
    }

    public void ajouterEnnemi(Ennemi e) {
        ennemis.add(e);
        ajouterBarreDeVie(e.getBarreDeVie());
    }

    public ObservableList<Vaisseau> getVaisseaux() {
        return vaisseaux;
    }

    public void ajouterVaisseau(Vaisseau v) {
        vaisseaux.add(v);
        ajouterBarreDeVie(v.getBarreDeVie());
    }

    public void verifVaisseau(Vaisseau vaisseau){
        if ((getArgent()-vaisseau.getPrix()) < 0){
            System.out.println("Pas assez d'argent");
        }else {
            if (vaisseau.vaisseauBienPlacee()) {
                ajouterVaisseau(vaisseau);
                suppArgent(vaisseau);
                System.out.println("Tourelle ajoutée");
            } else {
                System.out.println("Erreur ajout");
            }
        }
    }
    public void suppVaisseauPlacee(Vaisseau vaisseau){
        if (vaisseau.getVie() >= vaisseau.getVieMax()/2){
            getVaisseaux().remove(vaisseau);
        }
    }

    public ObservableList<BarreDeVie> getBarreDeVie() {
        return barreDeVies;
    }

    public void ajouterBarreDeVie(BarreDeVie b) {
        barreDeVies.add(b);
    }

    public void lancementVague() {
        if (getEnnemi().isEmpty() && getEnnemisVagues().isEmpty()) {
            setCompteurVague();
            lancerVague();
        }
    }

    public Vaisseau vaisseauPresent(int x, int y) {
        for (int i = 0; i < vaisseaux.size(); i++) {
            Vaisseau v = vaisseaux.get(i);
            if ((x / 32 == v.getX() / 32) && (y / 32 == v.getY() / 32)) {
                terrain.getTileMap()[y / 32][x / 32] = 4;
                if(v.estVivant() && v.getVie() >= v.getVieMax()/2){
                    boutique.ajoutVaisseau(v);
                }
                return v;
            }
        }
        return null;
    }

    public int getArgent() {
        return boutique.getArgent();
    }

    public void suppArgent(Vaisseau vaisseau) {
        boutique.suppression(vaisseau);
    }

    public IntegerProperty vieProperty() {
        return station.vieProperty();
    }

    public IntegerProperty argentProperty() {
        return boutique.argentProperty();
    }


    public IntegerProperty nbEnnemisProperty() {
        return nbEnnemis;
    }

    public final int getNbEnnemi() {
        return this.nbEnnemisProperty().getValue();
    }

    public final void setNbEnnemis() {
        if (ennemis.isEmpty()) {
            this.nbEnnemisProperty().setValue(0);
        } else {
            this.nbEnnemisProperty().setValue(ennemis.size());
        }
    }

    public int getTours() {
        return tours;
    }

    public void lancerVague() {
        vague.vagueEnnemis();
    }

    public ArrayList<Ennemi> getEnnemisVagues() {
        return vague.getEnnemisVague();
    }

    public void setCompteurVague() {
        vague.setCompteur();
    }

    public int getCompteurVague(){
        return vague.getCompteur();
    }

    public int getVieStation(){
        return station.getVie();
    }

    public IntegerProperty compteurVagueProperty() {
        return vague.compteurProperty();
    }

    public void unTour() {
        setNbEnnemis();

        if (!vague.getEnnemisVague().isEmpty() && tours % 5 == 0) {
            ajouterEnnemi(vague.getEnnemisVague().get(0));
            vague.supprimerEnnemi();
        }

        for (int i = 0; i < ennemis.size(); i++) {
            Ennemi a = ennemis.get(i);
            a.seDeplace();
            a.getBarreDeVie().setCouleur();
            a.getBarreDeVie().setX(a.getX());
            a.getBarreDeVie().setY(a.getY());
            a.getBarreDeVie().setVie(a.getPv());
            a.getBarreDeVie().setVieTotale();
            if (a.estArrive()) {
                ennemis.remove(i);
                station.perteVie();
            }
            if (!a.estVivant()) {
                ennemis.remove(i);
                boutique.ajoutEnnemi(a);
            }
        }
        for (int i = 0; i < vaisseaux.size(); i++) {
            Vaisseau v = vaisseaux.get(i);
            v.perteVie();
            v.ennemiPorteeVaisseau();
            v.attaque();
            v.getBarreDeVie().setCouleur();
            v.getBarreDeVie().setVie(v.getVie());
            v.getBarreDeVie().setVieTotale();
            if (!v.estVivant()) {
                vaisseauPresent(v.getX(), v.getY());
                vaisseaux.remove(i);
            }
            this.tours++;
        }
    }
}
