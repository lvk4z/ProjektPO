package agh.ics.oop.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GravePlantGenerator implements PlantGenerator{

    private final Random rand = new Random();
    private final int width, height;
    private final List<Vector2D> deadBodies;
    public GravePlantGenerator(int width, int height, List<Vector2D> deadBodies) {
        this.width = width;
        this.height=height;
        this.deadBodies = deadBodies;
    }

    @Override
    public Vector2D PlantGrass(int count) {
        float positionType = rand.nextFloat();
        List<Vector2D> allPositions = new ArrayList<>();
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++)allPositions.add(new Vector2D(j,i));
        }
        Collections.shuffle(allPositions,rand);
        if(deadBodies!=null)Collections.shuffle(deadBodies,rand);
        if(positionType>=0.2 && deadBodies!=null)return deadBodies.get(0);
        else return allPositions.get(0);
    }


}
