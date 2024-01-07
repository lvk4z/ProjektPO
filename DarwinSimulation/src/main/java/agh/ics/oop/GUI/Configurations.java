package agh.ics.oop.GUI;

import java.util.Map;

public class Configurations {

    private final int mapWidth;
    private final int mapHeight;
    private final int initialPlants;
    private final int singlePlantEnergy;
    private final int plantsGrowingEachDay;
    private final String plantDevelopmentOption;
    private final int initialAnimals;
    private final int initialAnimalsEnergy;
    private final int energyToBreed;
    private final int energyUsedForReproduction;
    private final int minMutations;
    private final int maxMutations;
    private final String mutationVariant;
    private final int genomeLength;

    public Configurations(Map<String, String> configDetails) {
        this.mapWidth = Integer.parseInt(configDetails.get("Map's width"));
        this.mapHeight = Integer.parseInt(configDetails.get("Map's height"));
        this.initialPlants = Integer.parseInt(configDetails.get("Initial Number of Plants"));
        this.singlePlantEnergy = Integer.parseInt(configDetails.get("Single plant energy"));
        this.plantsGrowingEachDay = Integer.parseInt(configDetails.get("Number of plants growing each day"));
        this.plantDevelopmentOption = configDetails.get("Plant Development Option");
        this.initialAnimals = Integer.parseInt(configDetails.get("Initial Number of Animals"));
        this.initialAnimalsEnergy = Integer.parseInt(configDetails.get("Initial animals' energy"));
        this.energyToBreed = Integer.parseInt(configDetails.get("Energy to consider the animal as full and ready to breed"));
        this.energyUsedForReproduction = Integer.parseInt(configDetails.get("Energy used for reproduction"));
        this.minMutations = Integer.parseInt(configDetails.get("Min number of mutations"));
        this.maxMutations = Integer.parseInt(configDetails.get("Max number of mutations"));
        this.mutationVariant = configDetails.get("Mutation variant");
        this.genomeLength = Integer.parseInt(configDetails.get("Genome length"));
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public int getInitialPlants() {
        return initialPlants;
    }

    public int getSinglePlantEnergy() {
        return singlePlantEnergy;
    }

    public int getPlantsGrowingEachDay() {
        return plantsGrowingEachDay;
    }

    public String getPlantDevelopmentOption() {
        return plantDevelopmentOption;
    }

    public int getInitialAnimals() {
        return initialAnimals;
    }

    public int getInitialAnimalsEnergy() {
        return initialAnimalsEnergy;
    }

    public int getEnergyToBreed() {
        return energyToBreed;
    }

    public int getEnergyUsedForReproduction() {
        return energyUsedForReproduction;
    }

    public int getMinMutations() {
        return minMutations;
    }

    public int getMaxMutations() {
        return maxMutations;
    }

    public String getMutationVariant() {
        return mutationVariant;
    }

    public int getGenomeLength() {
        return genomeLength;
    }
}