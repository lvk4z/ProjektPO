package agh.ics.oop.GUI;

public class Validator {

    public static void validateMapSize(int size) throws ValidationException {
        if (size < 1 || size > 100) {
            throw new ValidationException("Map size incorrect, try number between 1 and 100.");
        }
    }

    public static void validateInitialPlants(int count) throws ValidationException {
        if (count < 0) {
            throw new ValidationException("Initial number of plants cannot be negative.");
        }
    }

    public static void validateAnimalEnergyValue(int energy) throws ValidationException {
        if (energy < 0) {
            throw new ValidationException("Energy values cannot be negative.");
        }
    }

    public static void validateNumberOfPlantsGrowing(int count) throws ValidationException {
        if (count < 0) {
            throw new ValidationException("Number of plants growing each day cannot be negative.");
        }
    }

    public static void validateInitialAnimals(int count) throws ValidationException {
        if (count < 0) {
            throw new ValidationException("Initial number of animals cannot be negative.");
        }
    }

    public static void validateEnergyToBreed(int energy) throws ValidationException {
        if (energy < 0) {
            throw new ValidationException("Energy to breed cannot be negative.");
        }
    }

    public static void validateEnergyUsedForReproduction(int energy) throws ValidationException {
        if (energy < 0) {
            throw new ValidationException("Energy used for reproduction cannot be negative.");
        }
    }

    public static void validateMutationRange(int min, int max) throws ValidationException {
        if (min < 0 || max < 0) {
            throw new ValidationException("Mutation counts cannot be negative.");
        }
        if (min > max) {
            throw new ValidationException("Minimum mutations cannot be greater than maximum mutations.");
        }
    }

    public static void validateGenomeLength(int length) throws ValidationException {
        if (length <= 0) {
            throw new ValidationException("Genome length must be positive.");
        }
    }
}
