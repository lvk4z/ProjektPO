package agh.ics.oop.model;

import java.util.List;
import java.util.Set;

public interface PlantMap {
    void PlantGrass(int count);

    WorldElement plantAt(Vector2D position);

    void removePlant(Vector2D position);

    boolean isOccupied(Vector2D position);

    List<Plant> getAllPlants();
}
