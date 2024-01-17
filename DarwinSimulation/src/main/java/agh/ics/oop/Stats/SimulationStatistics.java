package agh.ics.oop.Stats;

import agh.ics.oop.model.Animals.Animal;
import agh.ics.oop.model.Plants.Plant;
import agh.ics.oop.model.WorldMap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimulationStatistics {
    private int currentDay;
    private int totalAnimals;
    private int totalPlants;
    private double averageEnergy;
    private double averageLifeSpanForDeadAnimals;
    private double averageNumberOfChildrenForLivingAnimals;
    private double totalLifeSpanOfDeadAnimals = 0;
    private int totalDeadAnimals = 0;
    private List<Integer> dominantGenotype;

    public SimulationStatistics() {
        this.currentDay = 0;
        this.totalAnimals = 0;
        this.totalPlants = 0;
        this.averageEnergy = 0.0;
        this.averageLifeSpanForDeadAnimals = 0.0;
        this.averageNumberOfChildrenForLivingAnimals = 0.0;
        this.dominantGenotype = null;
    }

    public void updateFromSimulation(WorldMap worldMap) {
        List<Animal> animals = worldMap.getMapInfo().getAllAnimals();
        List<Plant> plants = worldMap.getMapInfo().getAllPlants();

        currentDay = worldMap.getMapInfo().getDay();
        totalAnimals = animals.size();
        totalPlants = plants.size();
        averageEnergy = calculateAverageEnergy(animals);
        averageLifeSpanForDeadAnimals = calculateAverageLifeSpan(animals);
        averageNumberOfChildrenForLivingAnimals = calculateAverageNumberOfChildren(animals);
        dominantGenotype = calculateDominantGenotype(animals);
    }

    private List<Integer> calculateDominantGenotype(List<Animal> animals) {
        if (animals.isEmpty()) {
            return null;
        }

        Map<List<Integer>, Integer> genotypeFrequency = new HashMap<>();
        for (Animal animal : animals) {
            List<Integer> genotype = animal.getGenes();
            genotypeFrequency.put(genotype, genotypeFrequency.getOrDefault(genotype, 0) + 1);
        }

        Map.Entry<List<Integer>, Integer> dominantEntry = null;
        for (Map.Entry<List<Integer>, Integer> entry : genotypeFrequency.entrySet()) {
            if (dominantEntry == null || entry.getValue() > dominantEntry.getValue()) {
                dominantEntry = entry;
            }
        }

        return dominantEntry != null ? dominantEntry.getKey() : null;
    }

    private double calculateAverageEnergy(List<Animal> animals) {
        double result = Math.round(animals.stream().mapToInt(Animal::energy).average().orElse(0.0) * 100);
        return result / 100;
    }

    private double calculateAverageLifeSpan(List<Animal> animals) {
        for (Animal animal : animals) {
            if (!animal.isStillAlive()) {
                totalLifeSpanOfDeadAnimals += animal.getLifetime();
                totalDeadAnimals++;
            }
        }
        double result = Math.round(totalDeadAnimals > 0 ? totalLifeSpanOfDeadAnimals / totalDeadAnimals * 100 : 0.0);
        return result / 100;
    }

    private double calculateAverageNumberOfChildren(List<Animal> animals) {
        double result = Math.round(animals.stream().filter(Animal::isStillAlive).mapToInt(Animal::getKidsNumber).average().orElse(0.0));
        return result / 100;
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

    public List<Integer> getDominantGenotype() {
        return dominantGenotype;
    }

    public List<String> getStatisticsAsList() {
        return Arrays.asList(
                getCurrentDay(),
                getTotalAnimals(),
                getTotalPlants(),
                getAverageEnergy(),
                getAverageLifeSpanForDeadAnimals(),
                getAverageNumberOfChildrenForLivingAnimals()
        );
    }
}
