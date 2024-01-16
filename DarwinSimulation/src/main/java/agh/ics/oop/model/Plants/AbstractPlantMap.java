package agh.ics.oop.model.Plants;

import agh.ics.oop.model.Vector2D;
import agh.ics.oop.model.WorldElement;

import java.util.*;

public abstract class AbstractPlantMap implements PlantMap {
    protected final Map<Vector2D, Plant> plants = new HashMap<>();
    protected final int width, height, grassEnergy;
    protected abstract List<Vector2D> getPreferredPositions();
    @Override
    public List<Vector2D> getPreferredPositionsList() {
        return getPreferredPositions();
    }

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
        List<Vector2D> occupiedPositions = new ArrayList<>(plants.keySet());

        Collections.shuffle(preferredPosition, rand);
        Collections.shuffle(worsePosition, rand);

        preferredPosition.addAll(worsePosition);
        preferredPosition.removeAll(occupiedPositions);

        int i = 0,j = preferredPosition.size()-1, k=0;

        while(i<j && k<count){
            float positionType = rand.nextFloat();
            if (positionType >= 0.2) {
                plants.put(preferredPosition.get(i),new Plant(preferredPosition.get(i),grassEnergy));
                i++;
            }
            else{
                plants.put(preferredPosition.get(j),new Plant(preferredPosition.get(j),grassEnergy));
                j--;
            }
            k++;
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
    public List<Plant> getAllPlants(){
        return new ArrayList<>(plants.values());
    }

    protected List<Vector2D> getAllPossiblePositions() {
        List<Vector2D> allPositions = new ArrayList<>();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Vector2D position = new Vector2D(x, y);
                allPositions.add(position);
            }
        }
        return allPositions;
    }

    protected List<Vector2D> getWorsePositions() {
        List<Vector2D> allPossiblePositions = getAllPossiblePositions();
        allPossiblePositions.removeAll(getPreferredPositions());
        return allPossiblePositions;
    }

}
