package agh.ics.oop.model;

public interface PlantMap {
    void PlantGrass(int count);

    WorldElement plantAt(Vector2D position);

    void removePlant(Vector2D position);

}
