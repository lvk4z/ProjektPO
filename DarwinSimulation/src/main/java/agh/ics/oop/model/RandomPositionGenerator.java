package agh.ics.oop.model;

import java.util.*;

public abstract class RandomPositionGenerator implements Iterable<Vector2D>{
    protected final int maxWidth;
    protected final int maxHeight;
    private final int count;
    private final Random rand;
    private final List<Vector2D> shuffledPositions;

    public RandomPositionGenerator(int maxWidth, int maxHeight, int count) {
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
        this.count = count;
        this.rand = new Random();
        this.shuffledPositions = generateShuffledPositions();
    }

    @Override
    public Iterator<Vector2D> iterator() {
        return new Iterator<>() {
            private int generated = 0;

            @Override
            public boolean hasNext() {
                return generated < count;
            }

            @Override
            public Vector2D next() {
                generated++;
                return shuffledPositions.get(generated - 1);
            }
        };
    }

    protected List<Vector2D> generateShuffledPositions() {
        List<Vector2D> positions = new ArrayList<>();
        for (int x = 0; x < maxWidth; x++) {
            for (int y = 0; y < maxHeight; y++) {
                positions.add(new Vector2D(x, y));
            }
        }
        Collections.shuffle(positions, rand);
        return positions;
    }
}

