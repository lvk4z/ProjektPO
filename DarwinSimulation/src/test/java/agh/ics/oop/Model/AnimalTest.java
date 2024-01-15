package agh.ics.oop.Model;

import agh.ics.oop.Model.Animals.Animal;
import agh.ics.oop.Model.Animals.AnimalMap;
import agh.ics.oop.Model.Animals.Direction;
import agh.ics.oop.Model.Animals.Genotype;
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
        Animal animal = new Animal(new Vector2D(0,0),new Genotype(genes,false,0,0),50, Direction.NORTH);
        animal.move(map);
        assertEquals(animal.position(),result);
    }


    @Test
    void moveOutOfBorder() {
        AnimalMap map = new AnimalMap(6,6,0,0, null);
        List<Integer> genes = new ArrayList<>();
        genes.add(0);
        Vector2D result = new Vector2D(0,0);
        Animal animal = new Animal(new Vector2D(5,0),new Genotype(genes,false,0,0),50,Direction.EAST);
        animal.move(map);
        assertEquals(animal.position(),result);
    }

    @Test
    void moveCrossOfBorder() {
        AnimalMap map = new AnimalMap(6,6,0,0, null);
        List<Integer> genes = new ArrayList<>();
        genes.add(0);
        Vector2D result = new Vector2D(0,5);
        Animal animal = new Animal(new Vector2D(5,5),new Genotype(genes,false,0,0),50,Direction.NORTHEAST);
        animal.move(map);
        assertEquals(animal.position(),result);
        assertEquals(animal.getOrientation(),Direction.SOUTHWEST);
    }

    @Test
    void isStillAliveIfAlive() {
        List<Integer> genes = new ArrayList<>();
        genes.add(2);
        Animal animal = new Animal(new Vector2D(0,0),new Genotype(genes,false,0,0),50);
        assertTrue(animal.isStillAlive());
    }

    @Test
    void isStillAliveIfDead() {
        List<Integer> genes = new ArrayList<>();
        genes.add(2);
        Animal animal = new Animal(new Vector2D(0,0),new Genotype(genes,false,0,0),-1);
        assertFalse(animal.isStillAlive());
    }


    @Test
    void addKid() {
        Animal animal = new Animal(new Vector2D(0,0),new Genotype(List.of(0),false,0,0),0);
        animal.addKid();
        assertEquals(animal.getKidsNumber(),1);
    }

    @Test
    void addLifeLength() {
        Animal animal = new Animal(new Vector2D(0,0),new Genotype(List.of(0),false,0,0),0);
        animal.addLifeLength();
        assertEquals(animal.getLifetime(),1);
    }

    @Test
    void loseEnergy() {
        Animal animal = new Animal(new Vector2D(0,0),new Genotype(List.of(0),false,0,0),50);
        animal.loseEnergy(25);
        assertEquals(animal.energy(),25);
    }
}