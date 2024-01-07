package agh.ics.oop;

import agh.ics.oop.GUI.Configurations;
import agh.ics.oop.model.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Simulation implements Runnable {
    private final Configurations config;
    private final WorldMap map;
    private List<Animal> animals = new ArrayList<>();
    private final MapChangeListener mapChangeListener;
    int steps = 0;

    private final Random random = new Random();

    public Simulation(WorldMap map, Configurations config, MapChangeListener mapChangeListener) {
        this.config = config;
        this.map = map;
        this.mapChangeListener = mapChangeListener;
        map.plantGrass(config.getInitialPlants());
        List<Vector2D> positions = generatePositions(config.getInitialAnimals());
        for(int i=0;i<config.getInitialAnimals();i++){
            List<Integer> gen = generateGenotype(config.getGenomeLength());
            Vector2D position = positions.get(i);
            Animal animal = new Animal(position, gen, config.getInitialAnimalsEnergy());
            animals.add(animal);
            map.place(animal);
        }
    }

    @Override
    public void run() {
        while (steps < 100) {

            map.removeDeadAnimals();

            for (Animal animal : animals) {
                map.move(animal);
                mapChangeListener.mapChanged(map);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            map.eating();

            map.reproduction();

            map.plantGrass(config.getPlantsGrowingEachDay());

            animals = map.getAllAnimals();
            steps++;
        }
    }

    private List<Vector2D> generatePositions(int numberOfAnimals) {
        List<Vector2D> positions = new ArrayList<>();
        for(int i=0;i<numberOfAnimals;i++){
            int X = random.nextInt(config.getMapWidth());
            int Y = random.nextInt(config.getMapHeight());
            positions.add(new Vector2D(X,Y));
        }
        return positions;
    }

    private List<Integer> generateGenotype(int length) {
        List<Integer> genes = new ArrayList<>();
        for(int i=0;i<length;i++){
            int genotype = random.nextInt(8);
            genes.add(genotype);
        }
        return genes;
    }

}