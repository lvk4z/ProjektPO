package agh.ics.oop.model;

import java.util.List;
import java.util.Random;

public class Animal implements WorldElement {


    private Vector2D position;
    private Direction orientation;
    private final List<Integer> genes;
    private final int energy,lifetime,kids;

    Random rand = new Random();

    public Direction getRandomDirection(){
        Direction[] values = Direction.values();
        return values[rand.nextInt(8)];
    }

    public Animal(Vector2D position, List<Integer> genes, int energy) {
        this.position = position;
        orientation = getRandomDirection();
        this.genes = genes;
        this.energy = energy;
        lifetime=kids=0;
    }

    public void move(){
        int rotation = lifetime % genes.size();
        orientation = orientation.rotate(genes.get(rotation));
        Vector2D moveVector = orientation.toUnitVector();
        position=position.add(moveVector);
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
}
