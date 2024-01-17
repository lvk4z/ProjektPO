package agh.ics.oop.model;

import agh.ics.oop.model.Animals.Animal;
import agh.ics.oop.model.Animals.AnimalMap;
import agh.ics.oop.model.Plants.Plant;
import agh.ics.oop.model.Plants.PlantMap;

import java.util.List;

public class MapInfo {
    private final PlantMap plantMap;
    private final AnimalMap animalMap;

    public MapInfo(PlantMap plantMap, AnimalMap animalMap) {
        this.plantMap = plantMap;
        this.animalMap = animalMap;
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

    public List<Vector2D> getPreferredGrassPositions() {return plantMap.getPreferredPositionsList();}

    public int getDay() {return animalMap.getDay();}
}
