package com.example.sae.modele;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

}
