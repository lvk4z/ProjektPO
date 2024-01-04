package agh.ics.oop.model;

import java.util.List;

import static java.lang.Math.floor;

public class GravePlantGenerator extends RandomPositionGenerator{
    public GravePlantGenerator(int maxWidth, int maxHeight, int count) {
        super(maxWidth, maxHeight, count);
        deadBodies = null;
    }
    private final List<Vector2D> deadBodies;

    protected void addDeadBody(Vector2D deadBody){
        deadBodies.add(deadBody);
    }

    @Override
    protected List<Vector2D> generateShuffledPositions() {
        List<Vector2D> positions = super.generateShuffledPositions();
        int x = (int) (floor((double) (4 * maxWidth * maxHeight) /deadBodies.size()) - 1);
        for(Vector2D position : deadBodies){
            for(int i=0;i<x;i++)positions.add(position);
        }
        return positions;
    }

}
