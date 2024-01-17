package agh.ics.oop.GUI.Simulation;

import agh.ics.oop.GUI.Simulation.ValidationException;

public class Validator {

    private static int parseAndValidateNotEmpty(String value, String fieldName) throws ValidationException {
        if (value == null || value.trim().isEmpty()) {
            throw new ValidationException(fieldName + " cannot be empty.");
        }
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            throw new ValidationException(fieldName + " must be a valid number.");
        }
    }
    public static void validateMapSize(String size) throws ValidationException {
        int sizeInt = parseAndValidateNotEmpty(size, "Map size");
        if (sizeInt < 1 || sizeInt > 100) {
            throw new ValidationException("Map size incorrect, try number between 1 and 100.");
        }
    }

    public static void validateInitialPlants(String count) throws ValidationException {
        int countInt = parseAndValidateNotEmpty(count, "Initial number of plants");
        if (countInt < 0) {
            throw new ValidationException("Initial number of plants cannot be negative.");
        }
    }

    public static void validateEnergyValue(String energy) throws ValidationException {
        int energyInt = parseAndValidateNotEmpty(energy, "Energy value");
        if (energyInt < 0) {
            throw new ValidationException("Energy values cannot be negative.");
        }
    }

    public static void validateNumberOfPlantsGrowing(String count) throws ValidationException {
        int countInt = parseAndValidateNotEmpty(count, "Number of plants growing each day");
        if (countInt < 0) {
            throw new ValidationException("Number of plants growing each day cannot be negative.");
        }
    }

    public static void validateInitialAnimals(String count) throws ValidationException {
        int countInt = parseAndValidateNotEmpty(count, "Initial number of animals");
        if (countInt < 0) {
            throw new ValidationException("Initial number of animals cannot be negative.");
        }
    }

    public static void validateEnergyToBreed(String energy) throws ValidationException {
        int energyInt = parseAndValidateNotEmpty(energy, "Energy to breed");
        if (energyInt < 0) {
            throw new ValidationException("Energy to breed cannot be negative.");
        }
    }

    public static void validateEnergyUsedForReproduction(String energy) throws ValidationException {
        int energyInt = parseAndValidateNotEmpty(energy, "Energy used for reproduction");
        if (energyInt < 0) {
            throw new ValidationException("Energy used for reproduction cannot be negative.");
        }
    }

    public static void validateMutationRange(String min, String max) throws ValidationException {
        int minInt = parseAndValidateNotEmpty(min, "Minimum mutations");
        int maxInt = parseAndValidateNotEmpty(max, "Maximum mutations");
        if (minInt < 0 || maxInt < 0) {
            throw new ValidationException("Mutation counts cannot be negative.");
        }
        if (minInt > maxInt) {
            throw new ValidationException("Minimum mutations cannot be greater than maximum mutations.");
        }
    }

    public static void validateGenomeLength(String length) throws ValidationException {
        int lengthInt = parseAndValidateNotEmpty(length, "Genome length");
        if (lengthInt <= 0 || lengthInt >=20) {
            throw new ValidationException("Genome length incorrect.");
        }
    }
}