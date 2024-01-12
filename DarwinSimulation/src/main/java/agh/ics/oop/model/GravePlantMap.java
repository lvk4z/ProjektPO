package agh.ics.oop.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GravePlantMap extends AbstractPlantMap implements GraveChangeListener{

    private final List<Vector2D> deadBodies;
    public GravePlantMap(int width, int height, int grassEnergy) {
        super(width, height, grassEnergy);
        deadBodies = new ArrayList<>();
    }

    @Override
    protected List<Vector2D> getPreferredPositions() {
        return findPreferredPositions();
    }

    @Override
    protected List<Vector2D> getWorsePositions() {
        return findWorsePositions(getPreferredPositions());
    }


    private List<Vector2D> findPreferredPositions() {
        List<Vector2D> preferred = new ArrayList<>();
        List<Vector2D> surroundings = createSurroundings();

        for (Vector2D position : deadBodies) {
            for (Vector2D addon : surroundings) {
                Vector2D newPosition = position.add(addon);
                if (isWithinBounds(newPosition)) {
                    preferred.add(newPosition);
                }
            }
        }
        return preferred;
    }

    private List<Vector2D> createSurroundings() {
        return List.of(
                new Vector2D(0, 1), new Vector2D(0, -1),
                new Vector2D(1, 0), new Vector2D(-1, 0),
                new Vector2D(1, 1), new Vector2D(1, -1),
                new Vector2D(-1, 1), new Vector2D(-1, -1)
        );
    }

    private boolean isWithinBounds(Vector2D position) {
        return position.precedes(new Vector2D(width, height)) && position.follows(new Vector2D(0, 0));
    }

    private List<Vector2D> findWorsePositions(List<Vector2D> preferredPositions) {
        List<Vector2D> allPossiblePositions = getAllPossiblePositions();
        allPossiblePositions.removeAll(preferredPositions);
        return allPossiblePositions;
    }

    private List<Vector2D> getAllPossiblePositions() {
        List<Vector2D> allPositions = new ArrayList<>();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Vector2D position = new Vector2D(x, y);
                if (isWithinBounds(position)) {
                    allPositions.add(position);
                }
            }
        }
        return allPositions;
    }


    @Override
    public void addDeadBody(Vector2D body) {
        if(deadBodies.size()<10) deadBodies.add(body);
        else{
            deadBodies.remove(0);
            deadBodies.add(body);
        }
    }
}
