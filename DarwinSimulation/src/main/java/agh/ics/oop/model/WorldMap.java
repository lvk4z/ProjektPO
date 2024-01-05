package agh.ics.oop.model;

import java.util.List;

import static java.lang.Math.round;


public class WorldMap{

    private final PlantMap planMap;
    private final AnimalMap animalMap;

    public WorldMap(PlantMap planMap, AnimalMap animalMap) {
        this.planMap = planMap;
        this.animalMap = animalMap;
    }

    public void removeDeadAnimals(){
        animalMap.removeDeadAnimals();
    }

    public void move(Animal animal){
        animalMap.move(animal);
    }

    public void consumption(Vector2D position){
        List<Animal> animals = animalMap.animalAt(position);
        if(planMap.plantAt(position)!=null){
            animals.get(0).eat(planMap.plantAt(position));
            planMap.removePlant(position);
        }
    }

    public void reproduction(Vector2D position){
        animalMap.reproduction(position);
    }

    public void plantGrass(int count){
        planMap.PlantGrass(count);
    }


}
