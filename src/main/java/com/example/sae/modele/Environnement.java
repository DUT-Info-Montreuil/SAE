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
    private ObservableList<RayonLaser> rayonLasers;
    private IntegerProperty nombreEnnemis;
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
        this.rayonLasers = FXCollections.observableArrayList();
        this.tours = 0;
        this.terrain = terrain;
        this.station = new Station();
        this.boutique = new Boutique();
        this.vague = new Vague(terrain, this, boutique);
        this.nombreEnnemis = new SimpleIntegerProperty(0);
        this.chemin = BFS.bfs(terrain.getTileMap(),new Point(0, 3), new Point(21, 31));
        for (Point tuile : chemin) {
            System.out.println(tuile);
        }
    }

    public List<Point> getCheminCourt() {
        return chemin;
    }

    public ObservableList<BarreDeVie> getBarreDeVies() {
        return barreDeVies;
    }

    public void ajouterBarreDeVie(BarreDeVie b) {
        barreDeVies.add(b);
    }

    public ObservableList<Ennemi> getEnnemis() {
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

    public ObservableList<RayonLaser> getRayonLasers() {
        return rayonLasers;
    }

    public void ajouterRayonLaser(RayonLaser rayonLaser) {
        rayonLasers.add(rayonLaser);
    }

    public void supprimerRayonLaser(RayonLaser rayonLaser) {
        rayonLasers.remove(rayonLaser);
    }

    public void verifVaisseauCondition(Vaisseau vaisseau){
        if ((getArgent()-vaisseau.getPrix()) < 0){
            System.out.println("Pas assez d'argent");
        }else {
            if (vaisseau.vaisseauBienPlacee()) {
                ajouterVaisseau(vaisseau);
                debiterVaisseau(vaisseau);
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

    public void lancementVague() {
        if (getEnnemis().isEmpty() && getEnnemisVagues().isEmpty()) {
            incrementerVague();
            lancerVague();
        }
    }

    public Vaisseau vaisseauPresent(int x, int y) {
        for (int i = 0; i < vaisseaux.size(); i++) {
            Vaisseau v = vaisseaux.get(i);
            if ((x / 32 == v.getX() / 32) && (y / 32 == v.getY() / 32)) {
                terrain.getTileMap()[y / 32][x / 32] = 4;
                if(v.estVivant() && v.getVie() >= v.getVieMax()/2){
                    boutique.ajoutPrixEnleverVaisseau(v);
                }
                return v;
            }
        }
        return null;
    }

    public int getArgent() {
        return boutique.getArgent();
    }

    public void debiterVaisseau(Vaisseau vaisseau) {
        boutique.debiterPrixVaisseau(vaisseau);
    }

    public IntegerProperty vieProperty() {
        return station.vieProperty();
    }

    public IntegerProperty argentProperty() {
        return boutique.argentProperty();
    }


    public IntegerProperty nombreEnnemisProperty() {
        return nombreEnnemis;
    }

    public final void miseAJourNbEnnemis() {
        if (ennemis.isEmpty()) {
            this.nombreEnnemisProperty().setValue(0);
        } else {
            this.nombreEnnemisProperty().setValue(ennemis.size());
        }
    }

    public void lancerVague() {
        vague.vagueEnnemis();
    }

    public ArrayList<Ennemi> getEnnemisVagues() {
        return vague.getEnnemisVague();
    }

    public void incrementerVague() {
        vague.incrementerCompteur();
    }

    public int getCompteurVague(){
        return vague.getCompteurVague();
    }

    public int getVieStation(){
        return station.getVie();
    }

    public IntegerProperty compteurVagueProperty() {
        return vague.compteurVagueProperty();
    }

    public void unTour() {
        miseAJourNbEnnemis();

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
            a.getBarreDeVie().miseAJourVieTotale();
            if (a.estArriveStation()) {
                ennemis.remove(i);
                station.decrementerVie();
            }
            if (!a.estVivant()) {
                ennemis.remove(i);
                boutique.ajoutPrixEnnemiTuer(a);
            }
        }
        for (int i = 0; i < vaisseaux.size(); i++) {
            Vaisseau v = vaisseaux.get(i);
            v.perteVie();
            v.ennemiPorteeVaisseau();
            v.attaque();
            v.getBarreDeVie().setCouleur();
            v.getBarreDeVie().setVie(v.getVie());
            v.getBarreDeVie().miseAJourVieTotale();
            if (!v.estVivant()) {
                vaisseauPresent(v.getX(), v.getY());
                vaisseaux.remove(i);
            }
        }
            this.tours++;
    }
}
