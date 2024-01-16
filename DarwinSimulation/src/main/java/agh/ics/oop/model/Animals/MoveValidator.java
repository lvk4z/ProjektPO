package agh.ics.oop.model.Animals;

import agh.ics.oop.model.Vector2D;

public interface MoveValidator {
    Vector2D canMoveHorizontally(Vector2D position);
    boolean canMoveVertically(Vector2D position);
}
