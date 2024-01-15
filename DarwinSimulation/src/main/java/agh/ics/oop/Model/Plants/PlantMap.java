package agh.ics.oop.Model.Plants;

import agh.ics.oop.Model.Plants.Plant;
import agh.ics.oop.Model.Vector2D;
import agh.ics.oop.Model.WorldElement;

import java.util.List;

public interface PlantMap {
    void PlantGrass(int count);

    WorldElement plantAt(Vector2D position);

    void removePlant(Vector2D position);

    boolean isOccupied(Vector2D position);

    List<Plant> getAllPlants();

    List<Vector2D> getPreferredPositionsList();
}
