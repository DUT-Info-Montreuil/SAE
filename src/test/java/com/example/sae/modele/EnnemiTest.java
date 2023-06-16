package com.example.sae.modele;

import com.example.sae.BFS.BFS;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EnnemiTest {
    private Ennemi ennemi;
    private Terrain terrain;
    private Environnement environnement;

    @BeforeEach
    public void setUp() {
        terrain = new Terrain(1); // Initialisez le terrain selon vos besoins
        environnement = new Environnement(terrain); // Initialisez l'environnement selon vos besoins
        ennemi = new Ennemi(10, terrain, 100, environnement, 50, 150);
    }

    @Test
    public void testGetX() {
        assertEquals(50, ennemi.getX());
    }

    @Test
    public void testGetY() {
        assertEquals(0, ennemi.getY());
    }

    @Test
    public void testSetX() {
        ennemi.setX(100);
        assertEquals(100, ennemi.getX());
    }

    @Test
    public void testSetY() {
        ennemi.setY(50);
        assertEquals(50, ennemi.getY());
    }

    @Test
    public void testEstVivant() {
        assertTrue(ennemi.estVivant());

        ennemi.decrementerPv(50);
        assertTrue(ennemi.estVivant());

        ennemi.decrementerPv(100);
        assertFalse(ennemi.estVivant());
    }

    @Test
    public void testGetPv() {
        assertEquals(100, ennemi.getPv());
    }

    @Test
    public void testGetPvMax() {
        assertEquals(150, ennemi.getPvMax());
    }

    @Test
    public void testDecrementerPv() {
        ennemi.decrementerPv(50);
        assertEquals(50, ennemi.getPv());

        ennemi.decrementerPv(30);
        assertEquals(20, ennemi.getPv());

        ennemi.decrementerPv(20);
        assertEquals(0, ennemi.getPv());
    }

    @Test
    public void testEstArriveStation() {
        ennemi.setY(703);
        ennemi.setX(1023); // Se trouve sur la tuile de la station

        assertTrue(ennemi.estArriveStation());

        ennemi.setY(0); // Ne se trouve pas sur la tuile de la station
        assertFalse(ennemi.estArriveStation());
    }

 /*
    public void testSeDeplace() {
        ennemi.setX(0);
        ennemi.setY(0);

        int[][] terrain = {
                {1, 0, 1, 1, 1},
                {1, 0, 1, 0, 1},
                {1, 1, 1, 0, 1},
                {1, 0, 0, 0, 1},
                {1, 1, 1, 1, 2},
        };
        Point depart = new Point(0, 0);
        Point arrivee = new Point(4, 4);
        List<Point> chemin = BFS.bfs(terrain, depart, arrivee);
        environnement.setCheminCourt(chemin);
        System.out.println(chemin);

        // Effectuez les déplacements et vérifiez la position de l'ennemi après chaque déplacement
        ennemi.seDeplace(); // Déplacement 1
        assertEquals(32, ennemi.getX());
        assertEquals(0, ennemi.getY());

        ennemi.seDeplace(); // Déplacement 2
        assertEquals(64, ennemi.getX());
        assertEquals(0, ennemi.getY());

        ennemi.seDeplace(); // Déplacement 3
        assertEquals(96, ennemi.getX());
        assertEquals(0, ennemi.getY());

        ennemi.seDeplace(); // Déplacement 4
        assertEquals(128, ennemi.getX());
        assertEquals(0, ennemi.getY());

        ennemi.seDeplace(); // Déplacement 4
        assertEquals(128, ennemi.getX());
        assertEquals(32, ennemi.getY());

        ennemi.seDeplace(); // Déplacement 4
        assertEquals(128, ennemi.getX());
        assertEquals(64, ennemi.getY());

        ennemi.seDeplace(); // Déplacement 4
        assertEquals(128, ennemi.getX());
        assertEquals(96, ennemi.getY());

        ennemi.seDeplace(); // Déplacement 4
        assertEquals(128, ennemi.getX());
        assertEquals(128, ennemi.getY());
    }*/
}
