package agh.ics.oop.model.Animals;

import agh.ics.oop.model.*;

import java.util.List;
import java.util.Random;

public class Animal implements WorldElement {


    private Vector2D position;
    private Direction orientation;
    private final Genotype genes;
    private int energy,lifetime,kids,grassEaten,progeny;
    private int deathDay, nextActiveGen;
    private final Animal parent1,parent2;

    Random rand = new Random();

    public Direction getRandomDirection(){
        Direction[] values = Direction.values();
        return values[rand.nextInt(8)];
    }

    public Animal(Vector2D position, Genotype genes, int energy, Direction orientation, Animal parent1, Animal parent2) {
        this.position = position;
        this.orientation = orientation;
        this.genes = genes;
        this.energy = energy;
        this.parent1 = parent1;
        this.parent2 = parent2;
        lifetime=kids=grassEaten=deathDay=progeny=0;
        nextActiveGen = genes.getGenes().get(0);
    }

    public Animal(Vector2D position, Genotype genes, int energy, Animal parent1, Animal parent2){
        this(position, genes, energy, null, parent1, parent2);
        this.orientation = getRandomDirection();
    }

    public void move(MoveValidator validator){
        if(!genes.getGenes().isEmpty()) {
            int rotation = lifetime % genes.getGenes().size();
            rotate(genes.getGenes().get(rotation));
            moveBasedOnValidator(validator);
            nextActiveGen=genes.getGenes().get((lifetime+1) % genes.getGenes().size());
        }
    }

    private void moveBasedOnValidator(MoveValidator validator) {
        Vector2D moveVector = orientation.toUnitVector();
        Vector2D horizontalValidator = validator.canMoveHorizontally(position.add(moveVector));
        boolean verticalValidator = validator.canMoveVertically(position.add(moveVector));

        if (horizontalValidator == null && verticalValidator) {
            position = position.add(moveVector);
        } else if (horizontalValidator != null && !verticalValidator) {
            orientation = orientation.rotate(4);
            position = new Vector2D(horizontalValidator.getX(), horizontalValidator.getY() - 1);
        } else if (horizontalValidator != null) {
            position = horizontalValidator;
        } else {
            orientation = orientation.rotate(4);
        }
    }

    public void eat(WorldElement plant){
        energy+=plant.energy();
        grassEaten++;
    }

    public boolean isStillAlive(){
        return energy > 0;
    }

    @Override
    public Vector2D position() {
        return position;
    }

    @Override
    public int energy() {
        return energy;
    }

    public void addKid(){kids++;}

    public void addLifeLength(){lifetime++;}

    public Direction getOrientation() {return orientation;}
    public int getGrassEaten() {return grassEaten;}
    public int getDeathDay() {return deathDay;}
    public void setDeathDay(int day) {deathDay=day;}

    public int getNextActiveGen() {return nextActiveGen;}
    public List<Integer> getGenes() {return genes.getGenes();}

    public int getLifetime() {return lifetime;}

    public int getKidsNumber() {return kids;}

    public void loseEnergy(int lostEnergy) {energy -= lostEnergy;}
    public void rotate(int rotation){
        orientation = orientation.rotate(rotation);
    }

    public Genotype getGenotype() {return genes;}

    public Animal getParent1() {
        return parent1;
    }

    public Animal getParent2() {
        return parent2;
    }

    public void addProgeny() {progeny++;}

    public int getProgeny() {return progeny;}
}
