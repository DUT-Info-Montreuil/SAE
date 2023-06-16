package com.example.sae.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Collection;

public  class Vaisseau {

    private IntegerProperty x, y;
    protected Terrain terrain;
    private int prix;
    private int portee;
    private int degat;
    private Environnement env;
    private String id;
    private static int compteurId =0;
    private ObservableList<Ennemi> ennemis;
    private int vie;
    private BarreDeVie barreDeVie;
    private RayonLaser rayonLaser;
    private boolean rayonActif;

    public Vaisseau(int x, int y, Terrain terrain, int prix, Environnement env, int portee, int degat) {
        this.ennemis = FXCollections.observableArrayList();
        this.prix = prix;
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.terrain = terrain;
        this.env = env;
        this.portee = portee;
        this.degat = degat;
        this.id="V"+ compteurId;
        compteurId++;
        this.vie = 600;
        this.barreDeVie = new BarreDeVie(getVie(), getVieMax(), getId(), getX(), getY());
        this.rayonActif = false;
    }

    public IntegerProperty xProperty() {
        return x;
    }

    public IntegerProperty yProperty() {
        return y;
    }

    public String getId() {
        return id;
    }

    public boolean estVivant() {
        return this.vie > 0;
    }

    public final int getX() {
        return this.xProperty().getValue();
    }

    public final void setX(int n) {
        this.xProperty().setValue(n);
    }

    public final int getY() {
        return this.yProperty().getValue();
    }

    public final void setY(int n) {
        this.yProperty().setValue(n);
    }

    public int getPrix() {
        return prix;
    }

    public BarreDeVie getBarreDeVie() {
        return barreDeVie;
    }

    public boolean vaisseauBienPlacee() {
        int x = getX() / 32;
        int y = getY() / 32;
        System.out.println(y + " " + x);
        if (terrain.estAInterieurTerrain(x, y) && terrain.getTileMap()[y][x] == terrain.POSEV) {
            terrain.getTileMap()[y][x] = 5;
            return true; // La case n'est pas vide
        }
        return false;
    }

    public void ennemiPorteeVaisseau(){
        for (int i = 0; i < env.getEnnemis().size(); i++){
            Ennemi a = env.getEnnemis().get(i);
            if (ennemiPortee(a)){
                ennemis.add(a);
            }
            if (!ennemiPortee(a)){
                ennemis.remove(a);
            }
        }
    }

    public int getPortee() {
        return portee;
    }

    public void attaque(){
        boolean ennemi = false;
        for (int i = 0; i < ennemis.size(); i++){
                Ennemi a = ennemis.get(i);
                if (ennemiPortee(a) && a.estVivant() && !ennemi) {
                    if (!rayonActif){
                        rayonLaser = new RayonLaser(a.getX(), a.getY(), getX(), getY());
                        env.ajouterRayonLaser(rayonLaser);
                        rayonActif = true;
                    }
                    if (rayonActif){
                        rayonLaser.setxPositionEnnemi(a.getX());
                        rayonLaser.setyPositionEnnemi(a.getY());
                    }
                    a.decrementerPv(degat);
                    ennemi = true;
                    System.out.println("attaque" + i);
                } else {
                    if(!ennemi) {
                        env.supprimerRayonLaser(rayonLaser);
                        rayonLaser = null;
                        rayonActif = false;
                    }
                }
            }
        }

    public boolean ennemiPortee(Ennemi ennemi) {
        double distance = Math.sqrt(Math.pow(ennemi.getX() - getX() , 2) + Math.pow(ennemi.getY() - getY(), 2));
        return distance <= portee;
    }

    public int getVieMax(){
        return 600;
    }

    public int getVie() {
        return vie;
    }

    public void perteVie(){
        vie--;
    }

    public boolean isRayonActif(){
        return rayonActif;
    }

    public String getIdRayon() {
        return rayonLaser.getId();
    }

    public ObservableList<Ennemi> getEnnemis() {
        return ennemis;
    }
}