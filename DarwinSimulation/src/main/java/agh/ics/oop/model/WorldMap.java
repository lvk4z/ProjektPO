package agh.ics.oop.model;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Math.floor;


public class WorldMap {
    Random rand = new Random();
    private final int height;
    private final int width;
    private final int grassEnergy;
    private final int dayNumber;
    private final int reproductionEnergy;
    private final Map<Vector2D,Plant> plants = new HashMap<>();
    private final Map<Vector2D,List<Animal>> animals = new HashMap<>();

    public WorldMap(int height, int width, int plantNumber, int grassEnergy, int reproductionEnergy) {
        this.height = height;
        this.width = width;
        this.reproductionEnergy = reproductionEnergy;
        dayNumber = 0;
        placeGrass(plantNumber);
        this.grassEnergy=grassEnergy;
    }

    public void placeGrass(int plantNumber){
        IntStream.range(0, plantNumber).forEach(i -> {
            int x = rand.nextInt(width);
            int y = rand.nextInt(height);
            Vector2D plantPosition = new Vector2D(x, y);
            plants.put(plantPosition, new Plant(plantPosition, grassEnergy));
        });
    }

    public void place(Animal animal){
        if(animals.containsKey(animal.getPosition())){
            List<Animal> animalsAtPosition = animals.get(animal.getPosition());
            Animal firstAnimal = animalsAtPosition.get(0);
            if(firstAnimal.getEnergy()<=animal.getEnergy())animalsAtPosition.add(0,animal);
            else if(animalsAtPosition.size()>1){
                Animal secondAnimal = animalsAtPosition.get(1);
                if(secondAnimal.getEnergy()<=animal.getEnergy())animalsAtPosition.add(1,animal);
            }else animalsAtPosition.add(animal);
        }else animals.computeIfAbsent(animal.getPosition(), k -> new ArrayList<>()).add(animal);
    }

    public void move(Animal animal){
        if(animals.containsKey(animal.getPosition())) {
            List<Animal> animalsAtPosition = animals.get(animal.getPosition());
            animalsAtPosition.remove(animal);
            if (animalsAtPosition.isEmpty()) animals.remove(animal.getPosition());
            animal.move();
            this.place(animal);
        }
    }

    public WorldElement plantAt(Vector2D position) {
        return plants.get(position);
    }

    public void consumption(Vector2D position){
        if(plantAt(position) != null){
            List<Animal> animalsAtPosition = animals.get(position);
            animalsAtPosition.get(0).eat(plantAt(position));
            plants.remove(position);
        }
    }

    public boolean canReproduce(Animal animal){
        return animal.getEnergy()>=reproductionEnergy;
    }

    public void reproduction(Animal animal1, Animal animal2){
        int parentsEnergy = animal1.getEnergy() + animal2.getEnergy();
        int animal1Part = (int) floor(((double) animal1.getEnergy() / parentsEnergy) * animal1.getGenes().size());
        int animal2Part = animal2.getGenes().size() - animal1Part;
        int side = rand.nextInt(2);
        List<Integer> newGenes = new ArrayList<>();
        if ((side == 0 && animal1Part > animal2Part) || (side == 1 && animal2Part > animal1Part)) {
            newGenes.addAll(animal1.getGenes().stream().limit(animal1Part).toList());
            newGenes.addAll(animal2.getGenes().stream().skip(animal2.getGenes().size() - animal2Part).toList());
        } else if ((side == 0 && animal2Part > animal1Part) || (side == 1 && animal1Part > animal2Part)) {
            newGenes.addAll(animal2.getGenes().stream().limit(animal2Part).toList());
            newGenes.addAll(animal1.getGenes().stream().skip(animal1.getGenes().size() - animal1Part).toList());
        }
        animal1.loseEnergy(reproductionEnergy);
        animal2.loseEnergy(reproductionEnergy);
        Animal baby = new Animal(animal1.getPosition(),newGenes,reproductionEnergy);
        place(baby);
    }

}
