package agh.ics.oop.model;

import agh.ics.oop.model.Animals.Animal;
import agh.ics.oop.model.Animals.AnimalMap;
import agh.ics.oop.model.Animals.Direction;
import agh.ics.oop.model.Animals.Genotype;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {


    @Test
    void move() {
        AnimalMap map = new AnimalMap(6,6,0,0, null);
        List<Integer> genes = new ArrayList<>();
        genes.add(2);
        Vector2D result = new Vector2D(1,0);
        Animal animal = new Animal(new Vector2D(0,0),new Genotype(genes,false,0,0),50, Direction.NORTH, null, null);
        animal.move(map);
        assertEquals(animal.position(),result);
    }


    @Test
    void moveOutOfBorder() {
        AnimalMap map = new AnimalMap(6,6,0,0, null);
        List<Integer> genes = new ArrayList<>();
        genes.add(0);
        Vector2D result = new Vector2D(0,0);
        Animal animal = new Animal(new Vector2D(5,0),new Genotype(genes,false,0,0),50,Direction.EAST, null, null);
        animal.move(map);
        assertEquals(animal.position(),result);
    }

    @Test
    void moveCrossOfBorder() {
        AnimalMap map = new AnimalMap(6,6,0,0, null);
        List<Integer> genes = new ArrayList<>();
        genes.add(0);
        Vector2D result = new Vector2D(0,5);
        Animal animal = new Animal(new Vector2D(5,5),new Genotype(genes,false,0,0),50,Direction.NORTHEAST, null, null);
        animal.move(map);
        assertEquals(animal.position(),result);
        assertEquals(animal.getOrientation(),Direction.SOUTHWEST);
    }

    @Test
    void isStillAliveIfAlive() {
        List<Integer> genes = new ArrayList<>();
        genes.add(2);
        Animal animal = new Animal(new Vector2D(0,0),new Genotype(genes,false,0,0),50, null, null);
        assertTrue(animal.isStillAlive());
    }

    @Test
    void isStillAliveIfDead() {
        List<Integer> genes = new ArrayList<>();
        genes.add(2);
        Animal animal = new Animal(new Vector2D(0,0),new Genotype(genes,false,0,0),-1, null, null);
        assertFalse(animal.isStillAlive());
    }


    @Test
    void addKid() {
        Animal animal = new Animal(new Vector2D(0,0),new Genotype(List.of(0),false,0,0),0, null, null);
        animal.addKid();
        assertEquals(animal.getKidsNumber(),1);
    }

    @Test
    void addLifeLength() {
        Animal animal = new Animal(new Vector2D(0,0),new Genotype(List.of(0),false,0,0),0, null, null);
        animal.addLifeLength();
        assertEquals(animal.getLifetime(),1);
    }

    @Test
    void loseEnergy() {
        Animal animal = new Animal(new Vector2D(0,0),new Genotype(List.of(0),false,0,0),50, null, null);
        animal.loseEnergy(25);
        assertEquals(animal.energy(),25);
    }
}