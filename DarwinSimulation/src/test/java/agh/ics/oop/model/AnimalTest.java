package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    @Test
    void move() {
        List<Integer> genes = new ArrayList<>();
        genes.add(2);
        Vector2D result = new Vector2D(0,0);
        Animal animal = new Animal(new Vector2D(0,0),genes,50);
        Direction orientation = animal.getOrientation();
        orientation = orientation.rotate(2);
        result = result.add(orientation.toUnitVector());
        animal.move();
        assertEquals(animal.getPosition(),result);
    }

    @Test
    void isStillAliveIfAlive() {
        List<Integer> genes = new ArrayList<>();
        genes.add(2);
        Animal animal = new Animal(new Vector2D(0,0),genes,50);
        assertTrue(animal.isStillAlive());
    }

    @Test
    void isStillAliveIfDead() {
        List<Integer> genes = new ArrayList<>();
        genes.add(2);
        Animal animal = new Animal(new Vector2D(0,0),genes,-1);
        assertFalse(animal.isStillAlive());
    }
}