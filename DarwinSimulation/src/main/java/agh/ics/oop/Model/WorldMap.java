package agh.ics.oop.Model;

import agh.ics.oop.Model.Animals.Animal;
import agh.ics.oop.Model.Animals.AnimalMap;
import agh.ics.oop.Model.Plants.Plant;
import agh.ics.oop.Model.Plants.PlantMap;

import java.util.List;

public class WorldMap {
    private final PlantMap plantMap;
    private final AnimalMap animalMap;
    private int day = 0;

    public WorldMap(PlantMap planMap, AnimalMap animalMap) {
        this.plantMap = planMap;
        this.animalMap = animalMap;
    }

    public void removeDeadAnimals() {
        animalMap.removeDeadAnimals();
    }

    public void move(Animal animal) {
        animalMap.move(animal);
    }

    public void place(Animal animal) {
        animalMap.place(animal);
    }

    public void reproduction() {
        animalMap.reproduction();
    }

    public void eating() {
        List<Vector2D> positions = animalMap.getPositions();
        for (Vector2D position : positions) {
            if (plantMap.plantAt(position) != null) {
                Animal animal = animalMap.animalAt(position).get(0);
                animal.eat(plantMap.plantAt(position));
                plantMap.removePlant(position);
            }
        }
    }


    public void plantGrass(int count) {
        plantMap.PlantGrass(count);
        day++;
    }

    public Object objectAt(Vector2D position) {
        if (animalMap.isOccupied(position)) {
            return animalMap.animalAt(position);
        } else if (plantMap.isOccupied(position)) {
            return plantMap.plantAt(position);
        } else {
            return null;
        }
    }

    public List<Animal> getAllAnimals() {
        return animalMap.getAllAnimals();
    }

    public List<Plant> getAllPlants() {
        return plantMap.getAllPlants();
    }

    public int getDay() {
        return day;
    }

    public List<Vector2D> getPreferredGrassPositions() {return plantMap.getPreferredPositionsList();}
}
