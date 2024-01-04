package agh.ics.oop.model;

public class Plant implements WorldElement{

    private final Vector2D position;
    private final int energy;

    public Plant(Vector2D position, int energy) {
        this.position = position;
        this.energy = energy;
    }

    @Override
    public Vector2D getPosition() {
        return position;
    }

    @Override
    public int getEnergy() {
        return energy;
    }
}
