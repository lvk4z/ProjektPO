package agh.ics.oop.model;

import agh.ics.oop.model.Animals.Animal;
import agh.ics.oop.model.Animals.AnimalMap;
import agh.ics.oop.model.Plants.Plant;
import agh.ics.oop.model.Plants.PlantMap;

import java.util.List;

public class WorldMap {
    private final PlantMap plantMap;
    private final AnimalMap animalMap;
    private final MapInfo mapInfo;
    private int day = 0;

    public WorldMap(PlantMap planMap, AnimalMap animalMap) {
        this.plantMap = planMap;
        this.animalMap = animalMap;
        mapInfo = new MapInfo(this.plantMap,this.animalMap);
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

    public int getDay() {
        return day;
    }

    public MapInfo getMapInfo() {return mapInfo;}
}
