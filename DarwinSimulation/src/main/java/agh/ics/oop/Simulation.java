package agh.ics.oop;

import agh.ics.oop.GUI.Configurations;
import agh.ics.oop.model.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.List;

public class Simulation implements Runnable {
    private final Configurations config;
    private final WorldMap map;
    private final List<Animal> animals = new ArrayList<>();
    private final MapChangeListener mapChangeListener;
    int steps = 0;

    public Simulation(WorldMap map, Configurations config, MapChangeListener mapChangeListener) {
        this.config = config;
        this.map = map;
        this.mapChangeListener = mapChangeListener;


        List<Integer> gen = new ArrayList<>();
        gen.add(0);
        List<Integer> gen2 = new ArrayList<>();
        gen2.add(0);
        Animal animal = new Animal(new Vector2D(0, 2), gen, config.getInitialAnimalsEnergy(), Direction.EAST);
        Animal animal2 = new Animal(new Vector2D(5, 2), gen2, config.getInitialAnimalsEnergy(), Direction.WEST);
        animals.add(animal);
        animals.add(animal2);
        map.place(animal);
        map.place(animal2);
    }

    @Override
    public void run() {
        while (steps < 100) {
            for (Animal animal : animals) {
                map.move(animal);
                mapChangeListener.mapChanged(map);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            map.removeDeadAnimals();
            map.plantGrass(2);

            steps++;
        }
    }
}