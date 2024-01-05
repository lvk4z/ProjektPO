package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnimalMapTest {
    @Test
    void place() {
        AnimalMap map = new AnimalMap(4,4,0, 0);
        Animal animal = new Animal(new Vector2D(0,0),null,0);
        map.place(animal);
        List<Animal> animals = map.animalAt(animal.position());
        assertEquals(1, animals.size());
    }

    @Test
    void move() {
        AnimalMap map = new AnimalMap(4,4,0, 0);
        Animal animal = new Animal(new Vector2D(0,0),List.of(0),0,Direction.NORTH);
        map.place(animal);
        map.move(animal);
        List<Animal> animals = map.animalAt(new Vector2D(0,1));
        assertEquals(1, animals.size());
    }

    @Test
    void canReproduce() {
        AnimalMap map = new AnimalMap(4,4,50, 50);
        Animal animal = new Animal(new Vector2D(0,0),null,49);
        map.place(animal);
        assertFalse(map.canReproduce(animal));
    }

    @Test
    void reproduce() {
        AnimalMap map = new AnimalMap(4,4,20, 20);
        Animal parent1 = new Animal(new Vector2D(0,0),List.of(1,2,3,4),75);
        Animal parent2 = new Animal(new Vector2D(0,0),List.of(4,3,2,1),25);
        map.place(parent1);
        map.place(parent2);
        List<Integer> genes = map.reproduce(parent1,parent2);
        if(genes.get(0)==1)assertEquals(List.of(1, 2, 3, 1),genes);
        else assertEquals(List.of(4, 2, 3, 4),genes);
    }

    @Test
    void nextDay() {
        AnimalMap map = new AnimalMap(4,4,50, 50);
        for(int i=0;i<5;i++)map.nextDay();
        assertEquals(map.getDay(),5);
    }

    @Test
    void getAllAnimals() {
        AnimalMap map = new AnimalMap(4,4,0, 0);
        Animal animal1 = new Animal(new Vector2D(0,0),null,0);
        Animal animal2 = new Animal(new Vector2D(0,0),null,0);
        Animal animal3 = new Animal(new Vector2D(1,0),null,0);
        map.place(animal1);
        map.place(animal2);
        map.place(animal3);
        List<Animal> animals = map.getAllAnimals();
        assertEquals(animals.size(),3);
    }

    @Test
    void removeDeadAnimals() {
        AnimalMap map = new AnimalMap(4,4,0, 0);
        Animal animal1 = new Animal(new Vector2D(0,0),null,-1);
        Animal animal2 = new Animal(new Vector2D(0,0),null,-1);
        Animal animal3 = new Animal(new Vector2D(1,0),null,10);
        map.place(animal1);
        map.place(animal2);
        map.place(animal3);
        map.removeDeadAnimals();
        List<Animal> animals = map.getAllAnimals();
        assertEquals(animals.size(),1);
    }

}