package agh.ics.oop;

import agh.ics.oop.GUI.Simulation.Configurations;
import agh.ics.oop.Model.Animals.Animal;
import agh.ics.oop.Model.Animals.Genotype;
import agh.ics.oop.Model.Observers.MapChangeListener;
import agh.ics.oop.Stats.StatisticsExporter;
import agh.ics.oop.Stats.SimulationStatistics;
import agh.ics.oop.Model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Simulation implements Runnable {
    private final Configurations config;
    private final SimulationStatistics statistics;
    private final WorldMap map;
    private List<Animal> animals = new ArrayList<>();
    private final MapChangeListener mapChangeListener;
    int steps = 0;

    private volatile boolean isRunning = true;
    private boolean mutation = false;

    StatisticsExporter exporter;

    private final Random random = new Random();

    public Simulation(WorldMap map, Configurations config, MapChangeListener mapChangeListener) {
        this.config = config;
        this.statistics = new SimulationStatistics();
        this.map = map;
        this.mapChangeListener = mapChangeListener;
        map.plantGrass(config.getInitialPlants());
        List<Vector2D> positions = generatePositions(config.getInitialAnimals());
        if(config.getExport().equals("Export")) exporter = new StatisticsExporter(statistics);
        if(config.getMutationVariant().equals("Swap")) mutation = true;
        for(int i=0;i<config.getInitialAnimals();i++){
            Vector2D position = positions.get(i);
            Genotype genotype = new Genotype(config.getGenomeLength(),mutation,config.getMinMutations(),config.getMaxMutations());
            Animal animal = new Animal(position, genotype, config.getInitialAnimalsEnergy());
            animals.add(animal);
            map.place(animal);
        }
    }

    @Override
    public void run() {
        while (true){
            if (!isRunning) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                continue;
            }
            statistics.updateFromSimulation(map);
            map.removeDeadAnimals();
            if(exporter != null) exporter.updateStatistics();
            if(animals.isEmpty())break;
            for (Animal animal : animals) {
                map.move(animal);
                mapChangeListener.mapChanged(map,statistics);
                animal.loseEnergy(10);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            map.eating();
            map.reproduction();
            map.plantGrass(config.getPlantsGrowingEachDay());
            //mapChangeListener.dayPassed(statistics);
            animals = map.getAllAnimals();
            steps++;
        }
        mapChangeListener.mapChanged(map,statistics);
        if(exporter != null)exporter.exportToCSV();
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

    public void stopSimulation() {
        isRunning = false;
    }

    public void resumeSimulation() {
        isRunning = true;
    }

    public WorldMap getMap() {
        return map;
    }
}