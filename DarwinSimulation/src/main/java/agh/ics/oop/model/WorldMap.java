package agh.ics.oop.model;

import java.util.*;
import java.util.stream.IntStream;

public class WorldMap {
    Random rand = new Random();
    private final int height;
    private final int width;
    private final int grassEnergy;
    private final int dayNumber;
    private final Map<Vector2D,Plant> plants = new HashMap<>();

    private final Map<Vector2D,List<Animal>> animals = new HashMap<>();

    public WorldMap(int height, int width, int plantNumber, int grassEnergy) {
        this.height = height;
        this.width = width;
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
        animals.computeIfAbsent(animal.getPosition(), k -> new ArrayList<>()).add(animal);
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

}
