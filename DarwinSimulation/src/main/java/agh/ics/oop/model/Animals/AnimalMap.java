package agh.ics.oop.model.Animals;

import agh.ics.oop.model.Observers.GraveChangeListener;
import agh.ics.oop.model.Vector2D;

import java.util.*;

import static java.lang.Math.round;

public class AnimalMap implements MoveValidator {
    private final int height;
    private final int width;
    private final int reproductionEnergy;
    private final int energyToReproduce;
    private final Map<Vector2D, List<Animal>> animals = new HashMap<>();

    private final GraveChangeListener changeListener;
    Random rand = new Random();
    private int dayNumber;

    public AnimalMap(int height, int width, int reproductionEnergy, int energyToReproduce, GraveChangeListener changeListener) {
        this.height = height;
        this.width = width;
        this.reproductionEnergy = reproductionEnergy;
        this.energyToReproduce = energyToReproduce;
        this.changeListener = changeListener;
        dayNumber = 0;
    }

    public void place(Animal animal) {
        if (animals.containsKey(animal.position())) {
            List<Animal> animalsAtPosition = animals.get(animal.position());
            Animal firstAnimal = animalsAtPosition.get(0);
            if (firstAnimal.energy() <= animal.energy()) {
                animalsAtPosition.add(0, animal);
                return;
            }
            else if (animalsAtPosition.size() > 1) {
                Animal secondAnimal = animalsAtPosition.get(1);
                if (secondAnimal.energy() <= animal.energy()) {
                    animalsAtPosition.add(1, animal);
                    return;
                }
            }
            animalsAtPosition.add(animal);
        } else{
            animals.computeIfAbsent(animal.position(), k -> new ArrayList<>()).add(animal);
        }
    }

    public void move(Animal animal) {
        if (animals.containsKey(animal.position())) {
            List<Animal> animalsAtPosition = animals.get(animal.position());
            animalsAtPosition.remove(animal);
            if (animalsAtPosition.isEmpty()) animals.remove(animal.position());
            animal.move(this);
            animal.addLifeLength();
            this.place(animal);
        }
    }

    public List<Animal> animalAt(Vector2D position) {
        if (animals.containsKey(position)) {
            return animals.get(position);
        }
        return null;
    }

    public boolean canReproduce(Animal animal) {
        return animal.energy() >= energyToReproduce;
    }

    public void reproduction() {
        for(Vector2D position : (animals.keySet())) {
            List<Animal> animals = animalAt(position);
            if (animals.size() > 1) {
                Animal parent1 = animals.get(0);
                Animal parent2 = animals.get(1);
                if (canReproduce(parent1) && canReproduce(parent2)) {
                    List<Integer> newGenes = reproduce(parent1, parent2);
                    parent1.loseEnergy(reproductionEnergy);
                    parent1.addKid();
                    parent2.loseEnergy(reproductionEnergy);
                    parent2.addKid();
                    Genotype genotype = parent1.getGenotype();
                    Animal baby = new Animal(position, new Genotype(newGenes,genotype.getMutationOption(),genotype.getMinimalMutationNumber(),genotype.getMaximalMutationNumber()), 2 * reproductionEnergy, parent1, parent2);
                    addProgeny(baby,new HashSet<Animal>());
                    baby.getGenotype().mutate();
                    this.place(baby);
                }
            }
        }
    }

    public List<Integer> reproduce(Animal animal1, Animal animal2) {
        int parentsEnergy = animal1.energy() + animal2.energy();
        int animal1Part = (int) round(((double) animal1.energy() / parentsEnergy) * animal1.getGenes().size());
        int animal2Part = animal2.getGenes().size() - animal1Part;
        int side = rand.nextInt(2);
        List<Integer> newGenes = new ArrayList<>();
        if ((side == 0 && animal1Part >= animal2Part) || (side == 1 && animal2Part >= animal1Part)) {
            newGenes.addAll(animal1.getGenes().stream().limit(animal1Part).toList());
            newGenes.addAll(animal2.getGenes().stream().skip(animal2.getGenes().size() - animal2Part).toList());
        } else {
            newGenes.addAll(animal2.getGenes().stream().limit(animal2Part).toList());
            newGenes.addAll(animal1.getGenes().stream().skip(animal1.getGenes().size() - animal1Part).toList());
        }
        return newGenes;
    }

    public void addProgeny(Animal animal, HashSet<Animal> visited){
        Animal parent1 = animal.getParent1();
        Animal parent2 = animal.getParent2();
        if(parent1 != null && parent2 != null) {
            if(!visited.contains(parent1)){
                parent1.addProgeny();
                visited.add(parent1);
            }
            if(!visited.contains(parent2)){
                parent2.addProgeny();
                visited.add(parent2);
            }
            addProgeny(parent1,visited);
            addProgeny(parent2,visited);
        }
    }


    @Override
    public Vector2D canMoveHorizontally(Vector2D position) {
        if (position.getX() >= width) return new Vector2D(0, position.getY());
        if (position.getX() < 0) return new Vector2D(width - 1, position.getY());
        else return null;
    }

    @Override
    public boolean canMoveVertically(Vector2D position) {
        if (position.getY() >= height) return false;
        return !(position.getY() < 0);
    }

    public void nextDay() {
        dayNumber++;
    }

    public List<Animal> getAllAnimals() {
        List<Animal> allAnimals = new ArrayList<>();
        for (List<Animal> animalList : animals.values()) {
            allAnimals.addAll(animalList);
        }
        return allAnimals;
    }

    public void removeDeadAnimals() {
        List<Animal> allAnimals = getAllAnimals();
        for (Animal animal : allAnimals) {
            if (animal.energy() <= 0) {
                animal.setDeathDay(dayNumber);
                List<Animal> animalsAtPosition = animals.get(animal.position());
                animalsAtPosition.remove(animal);
                if(changeListener != null)changeListener.addDeadBody(animal.position());
                if (animalsAtPosition.isEmpty()) animals.remove(animal.position());
            }
        }
    }

    public int getDay() {
        return dayNumber;
    }

    public boolean isOccupied(Vector2D position) {
        return animals.containsKey(position);
    }

    public List<Vector2D> getPositions(){
        return new ArrayList<>(animals.keySet());
    }

}
