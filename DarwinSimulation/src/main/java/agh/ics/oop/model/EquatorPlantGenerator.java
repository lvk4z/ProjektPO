package agh.ics.oop.model;

import java.util.*;

import static java.lang.Math.floor;
import static java.lang.Math.round;

public class EquatorPlantGenerator implements PlantGenerator {

    private final int width, height;
    private final Random rand = new Random();

    public EquatorPlantGenerator(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public Vector2D PlantGrass(int count) {
        int preferredHeight = (int) Math.round(0.2 * height);
        int firstHeight = (int) Math.floor((float) (height - preferredHeight) / 2) + 1;

        List<Vector2D> preferredPosition = populatePositionRange(firstHeight, firstHeight + preferredHeight, width);
        List<Vector2D> worsePosition = new ArrayList<>(populatePositionRange(0, firstHeight, width));
        worsePosition.addAll(populatePositionRange(firstHeight + preferredHeight, height, width));

        Collections.shuffle(preferredPosition, rand);
        Collections.shuffle(worsePosition, rand);


        float positionType = rand.nextFloat();
        if (positionType >= 0.2) return preferredPosition.get(0);
        else return worsePosition.get(0);


    }

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