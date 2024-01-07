package agh.ics.oop.model;

public class WorldMap {
    private final PlantMap plantMap;
    private final AnimalMap animalMap;

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

    public void reproduction(Vector2D position) {
        animalMap.reproduction(position);
    }


    public void plantGrass(int count) {
        plantMap.PlantGrass(count);
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

}
