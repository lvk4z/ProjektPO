package agh.ics.oop.model;

import java.util.List;

import static java.lang.Math.floor;

public class EquatorPlantGenerator extends RandomPositionGenerator {

    public EquatorPlantGenerator(int maxWidth, int maxHeight, int count) {
        super(maxWidth, maxHeight, count);
    }

    @Override
    protected List<Vector2D> generateShuffledPositions() {
        int preferredHeight = (int) floor(0.2*maxWidth);
        int preferredPositionsNumber = (int) ((((preferredHeight+maxHeight)*0.8 - (maxWidth*maxHeight)))/(preferredHeight*maxHeight))-1;
        List<Vector2D> positions = super.generateShuffledPositions();
        for(int k=0;k<preferredPositionsNumber;k++) {
            for (int i = 0; i < maxWidth; i++) {
                for (int j = preferredHeight; j < maxHeight - preferredHeight; j++) {
                    positions.add(new Vector2D(i, j));
                }
            }
        }
        return positions;
    }
}
