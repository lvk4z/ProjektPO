package agh.ics.oop.model;

import java.util.List;
import java.util.Random;

public class Animal implements WorldElement {


    private Vector2D position;
    private Direction orientation;
    private final List<Integer> genes;
    private int energy,lifetime,kids;

    Random rand = new Random();

    public Direction getRandomDirection(){
        Direction[] values = Direction.values();
        return values[rand.nextInt(8)];
    }

    public Animal(Vector2D position, List<Integer> genes, int energy, Direction orientation) {
        this.position = position;
        this.orientation = orientation;
        this.genes = genes;
        this.energy = energy;
        lifetime=kids=0;
    }

    public Animal(Vector2D position, List<Integer> genes, int energy){
        this(position, genes, energy, null);
        this.orientation = getRandomDirection();
    }

    public void move(){
        int rotation = lifetime % genes.size();
        rotate(genes.get(rotation));
        Vector2D moveVector = orientation.toUnitVector();
        position=position.add(moveVector);
    }

    public void eat(WorldElement plant){
        energy+=plant.getEnergy();
    }

    public boolean isStillAlive(){
        return energy > 0;
    }

    @Override
    public Vector2D getPosition() {
        return position;
    }

    @Override
    public int getEnergy() {
        return energy;
    }

    public Direction getOrientation() {return orientation;}

    public List<Integer> getGenes() {return genes;}

    public int getLifetime() {return lifetime;}

    public void loseEnergy(int lostEnergy) {energy -= lostEnergy;}
    public void rotate(int rotation){
        orientation = orientation.rotate(rotation);
    }
}
