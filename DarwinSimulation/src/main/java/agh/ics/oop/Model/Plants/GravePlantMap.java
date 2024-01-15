package agh.ics.oop.Model.Plants;

import agh.ics.oop.Model.Observers.GraveChangeListener;
import agh.ics.oop.Model.Vector2D;

import java.util.ArrayList;
import java.util.List;

public class GravePlantMap extends AbstractPlantMap implements GraveChangeListener {

    private final List<Vector2D> deadBodies;
    public GravePlantMap(int width, int height, int grassEnergy) {
        super(width, height, grassEnergy);
        deadBodies = new ArrayList<>();
    }

    @Override
    protected List<Vector2D> getPreferredPositions() {
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


    @Override
    public void addDeadBody(Vector2D body) {
        if(deadBodies.size()<=10) deadBodies.add(body);
        else{
            deadBodies.remove(0);
            deadBodies.add(body);
        }
    }
}
