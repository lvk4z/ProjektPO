package agh.ics.oop.model.Plants;

import agh.ics.oop.model.Vector2D;
import agh.ics.oop.model.WorldElement;

import java.util.List;

public interface PlantMap {
    void PlantGrass(int count);

    WorldElement plantAt(Vector2D position);

    void removePlant(Vector2D position);

    boolean isOccupied(Vector2D position);

    List<Plant> getAllPlants();

    List<Vector2D> getPreferredPositionsList();
}
