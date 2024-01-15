package agh.ics.oop.Model.Plants;

import agh.ics.oop.Model.Plants.AbstractPlantMap;
import agh.ics.oop.Model.Vector2D;

import java.util.*;

import static java.lang.Math.round;

public class EquatorPlantMap extends AbstractPlantMap {

    public EquatorPlantMap(int width, int height, int grassEnergy) {
        super(width, height, grassEnergy);
    }

    @Override
    protected List<Vector2D> getPreferredPositions() {
        int preferredHeight = (int) Math.round(0.2 * height);
        int firstHeight = (int) Math.floor((float) (height - preferredHeight) / 2);

        List<Vector2D> positions = new ArrayList<>();
        for (int i = firstHeight; i < firstHeight + preferredHeight; i++) {
            for (int j = 0; j < width; j++) {
                positions.add(new Vector2D(j, i));
            }
        }
        return positions;

    }




}