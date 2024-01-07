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
            boolean flag = false;
            float positionType = rand.nextFloat();
            if (positionType >= 0.2) {
                while(j<preferredPosition.size() && plantAt(preferredPosition.get(j))!=null)j++;
                if(j<preferredPosition.size()){
                    plants.put(preferredPosition.get(j),new Plant(preferredPosition.get(j),grassEnergy));
                    flag = true;
                }
            }
            if(!flag){
                while(k<worsePosition.size() && plantAt(worsePosition.get(k))!=null)k++;
                if(k<worsePosition.size()){
                    plants.put(worsePosition.get(k),new Plant(worsePosition.get(k),grassEnergy));
                    flag = true;
                }
            }
            if(!flag)break;
        }
    }

    @Override
    public WorldElement plantAt(Vector2D position) {
        return plants.get(position);
    }

    @Override
    public void removePlant(Vector2D position){plants.remove(position);}

    public boolean isOccupied(Vector2D position) {
        return plants.containsKey(position);
    }

}
