package agh.ics.oop.model;

import java.util.*;

public abstract class AbstractPlantMap implements PlantMap{
    protected final Map<Vector2D,Plant> plants = new HashMap<>();

    protected final int width, height, grassEnergy;

    protected abstract List<Vector2D> getPreferredPositions();

    protected abstract List<Vector2D> getWorsePositions();
    private final Random rand = new Random();

    protected AbstractPlantMap(int width, int height, int grassEnergy) {
        this.width = width;
        this.height = height;
        this.grassEnergy = grassEnergy;
    }

    @Override
    public void PlantGrass(int count) {

        List<Vector2D> preferredPosition = getPreferredPositions();
        List<Vector2D> worsePosition = getWorsePositions();

        Collections.shuffle(preferredPosition, rand);
        Collections.shuffle(worsePosition, rand);

        int j = 0,k = 0;

        for (int i = 0; i < count; i++) {
            float positionType = rand.nextFloat();
            if (positionType >= 0.2) {
                while(plantAt(preferredPosition.get(j))!=null && j<preferredPosition.size())j++;
                if(j<preferredPosition.size())plants.put(preferredPosition.get(j),new Plant(preferredPosition.get(j),grassEnergy));
            }else {
                while(plantAt(worsePosition.get(k))!=null && k<worsePosition.size())k++;
                if(k<worsePosition.size())plants.put(worsePosition.get(k),new Plant(worsePosition.get(k),grassEnergy));
            }
        }
    }

    @Override
    public WorldElement plantAt(Vector2D position) {
        return plants.get(position);
    }

    @Override
    public void removePlant(Vector2D position){plants.remove(position);}
}
