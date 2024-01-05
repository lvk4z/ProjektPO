package agh.ics.oop.model;

import java.util.*;

import static java.lang.Math.round;

public class AnimalMap implements MoveValidator{
    Random rand = new Random();
    private final int height;
    private final int width;
    private int dayNumber;
    private final int reproductionEnergy;

    private final int energyToReproduce;
    private final Map<Vector2D, List<Animal>> animals = new HashMap<>();

    public AnimalMap(int height, int width, int reproductionEnergy, int energyToReproduce) {
        this.height = height;
        this.width = width;
        this.reproductionEnergy = reproductionEnergy;
        this.energyToReproduce = energyToReproduce;
        dayNumber = 0;
    }

    public void place(Animal animal){
        if(animals.containsKey(animal.position())){
            List<Animal> animalsAtPosition = animals.get(animal.position());
            Animal firstAnimal = animalsAtPosition.get(0);
            if(firstAnimal.energy()<=animal.energy())animalsAtPosition.add(0,animal);
            else if(animalsAtPosition.size()>1){
                Animal secondAnimal = animalsAtPosition.get(1);
                if(secondAnimal.energy()<=animal.energy())animalsAtPosition.add(1,animal);
            }else animalsAtPosition.add(animal);
        }else animals.computeIfAbsent(animal.position(), k -> new ArrayList<>()).add(animal);
    }

    public void move(Animal animal){
        if(animals.containsKey(animal.position())) {
            List<Animal> animalsAtPosition = animals.get(animal.position());
            animalsAtPosition.remove(animal);
            if (animalsAtPosition.isEmpty()) animals.remove(animal.position());
            animal.move(this);
            this.place(animal);
        }
    }

    public List<Animal> animalAt(Vector2D position) {
        if(animals.containsKey(position)) {
            return animals.get(position);
        }
        return null;
    }

    public boolean canReproduce(Animal animal){
        return animal.energy()>=energyToReproduce;
    }

    public void reproduction(Vector2D position){
        List<Animal> animals = animalAt(position);
        if(animals.size()>1) {
            Animal parent1 = animals.get(0);
            Animal parent2 = animals.get(1);
            if (canReproduce(parent1) && canReproduce(parent2)) {
                List<Integer> newGenes = reproduce(parent1, parent2);
                parent1.loseEnergy(reproductionEnergy);
                parent2.loseEnergy(reproductionEnergy);
                Animal baby = new Animal(position, newGenes, 2 * reproductionEnergy);
                place(baby);

            }
        }
    }

    public List<Integer> reproduce(Animal animal1, Animal animal2){
        int parentsEnergy = animal1.energy() + animal2.energy();
        int animal1Part = (int) round(((double) animal1.energy() / parentsEnergy) * animal1.getGenes().size());
        int animal2Part = animal2.getGenes().size() - animal1Part;
        int side = rand.nextInt(2);
        List<Integer> newGenes = new ArrayList<>();
        if ((side == 0 && animal1Part > animal2Part) || (side == 1 && animal2Part > animal1Part)) {
            newGenes.addAll(animal1.getGenes().stream().limit(animal1Part).toList());
            newGenes.addAll(animal2.getGenes().stream().skip(animal2.getGenes().size() - animal2Part).toList());
        } else if ((side == 0 && animal2Part > animal1Part) || (side == 1 && animal1Part > animal2Part)) {
            newGenes.addAll(animal2.getGenes().stream().limit(animal2Part).toList());
            newGenes.addAll(animal1.getGenes().stream().skip(animal1.getGenes().size() - animal1Part).toList());
        }
        return newGenes;
    }

    @Override
    public Vector2D canMoveHorizontally(Vector2D position) {
        if(position.getX()>=width)return new Vector2D(0,position.getY());
        if(position.getX()<0)return new Vector2D(width-1,position.getY());
        else return null;
    }

    @Override
    public boolean canMoveVertically(Vector2D position) {
        if(position.getY()>=height)return false;
        return !(position.getY()<0);
    }

    public void nextDay(){dayNumber++;}

    public List<Animal> getAllAnimals() {
        List<Animal> allAnimals = new ArrayList<>();
        for (List<Animal> animalList : animals.values()) {
            allAnimals.addAll(animalList);
        }
        return allAnimals;
    }

    public void removeDeadAnimals(){
        List<Animal> allAnimals = getAllAnimals();
        for (Animal animal : allAnimals){
            if(animal.energy()<=0){
                List<Animal> animalsAtPosition = animals.get(animal.position());
                animalsAtPosition.remove(animal);
                if (animalsAtPosition.isEmpty()) animals.remove(animal.position());
            }
        }
    }

    public int getDay(){return dayNumber;}
}