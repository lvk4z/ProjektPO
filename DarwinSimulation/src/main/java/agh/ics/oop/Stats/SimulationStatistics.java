package agh.ics.oop.Stats;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.Plant;
import agh.ics.oop.model.WorldMap;

import java.util.List;

public class SimulationStatistics {
    private int currentDay;
    private int totalAnimals;
    private int totalPlants;
    private double averageEnergy;
    private double averageLifeSpanForDeadAnimals;
    private double averageNumberOfChildrenForLivingAnimals;
    private double totalLifeSpanOfDeadAnimals = 0;
    private int totalDeadAnimals = 0;
    public SimulationStatistics() {
        this.currentDay = 0;
        this.totalAnimals = 0;
        this.totalPlants = 0;
        this.averageEnergy = 0.0;
        this.averageLifeSpanForDeadAnimals = 0.0;
        this.averageNumberOfChildrenForLivingAnimals = 0.0;
    }

    public void updateFromSimulation(WorldMap worldMap) {
        List<Animal> animals = worldMap.getAllAnimals();
        List<Plant> plants = worldMap.getAllPlants();

        this.currentDay = worldMap.getDay();
        this.totalAnimals = animals.size();
        this.totalPlants = plants.size();
        this.averageEnergy = calculateAverageEnergy(animals);
        this.averageLifeSpanForDeadAnimals = calculateAverageLifeSpan(animals);
        this.averageNumberOfChildrenForLivingAnimals = calculateAverageNumberOfChildren(animals);
    }

    private double calculateAverageEnergy(List<Animal> animals) {
        double result = Math.round(animals.stream().mapToInt(Animal::energy).average().orElse(0.0) * 100);
        return  result/100;
    }

    private double calculateAverageLifeSpan(List<Animal> animals) {
        for (Animal animal : animals) {
            if(!animal.isStillAlive()) {
                totalLifeSpanOfDeadAnimals += animal.getLifetime();
                totalDeadAnimals++;
            }
        }
        double result = Math.round(totalDeadAnimals > 0 ? totalLifeSpanOfDeadAnimals / totalDeadAnimals * 100 : 0.0 );
        return result/100;
}

    private double calculateAverageNumberOfChildren(List<Animal> animals) {
        double result = Math.round(animals.stream().filter(Animal::isStillAlive).mapToInt(Animal::getKidsNumber).average().orElse(0.0));
        return result/100;
    }


    public String getTotalAnimals() {
        return String.valueOf(totalAnimals);
    }

    public String getCurrentDay() {
        return String.valueOf(currentDay);
    }

    public String getTotalPlants() {
        return String.valueOf(totalPlants);
    }

    public String getAverageEnergy() {
        return String.valueOf(averageEnergy);
    }

    public String getAverageLifeSpanForDeadAnimals() {
        return String.valueOf(averageLifeSpanForDeadAnimals);
    }

    public String getAverageNumberOfChildrenForLivingAnimals() {
        return String.valueOf(averageNumberOfChildrenForLivingAnimals);
    }
}
