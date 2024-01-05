package agh.ics.oop.model;

import java.util.*;

import static java.lang.Math.round;

public class EquatorPlantMap extends AbstractPlantMap {

    protected EquatorPlantMap(int width, int height, int grassEnergy) {
        super(width, height, grassEnergy);
    }

    @Override
    protected List<Vector2D> getPreferredPositions() {
        int preferredHeight = (int) Math.round(0.2 * height);
        int firstHeight = (int) Math.floor((float) (height - preferredHeight) / 2) + 1;

        return populatePositionRange(firstHeight, firstHeight + preferredHeight, width);
    }

    @Override
    protected List<Vector2D> getWorsePositions() {
        int preferredHeight = (int) Math.round(0.2 * height);
        int firstHeight = (int) Math.floor((float) (height - preferredHeight) / 2) + 1;

        List<Vector2D> worsePosition = new ArrayList<>(populatePositionRange(0, firstHeight, width));
        worsePosition.addAll(populatePositionRange(firstHeight + preferredHeight, height, width));

        return worsePosition;
    }


    @Override
    public void PlantGrass(int count) {}



    private List<Vector2D> populatePositionRange(int startY, int endY, int width) {
        List<Vector2D> positions = new ArrayList<>();
        for (int i = startY; i < endY; i++) {
            for (int j = 0; j < width; j++) {
                positions.add(new Vector2D(j, i));
            }
        }
        return positions;
    }
}