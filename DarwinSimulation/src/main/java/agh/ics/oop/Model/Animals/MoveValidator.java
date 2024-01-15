package agh.ics.oop.Model.Animals;

import agh.ics.oop.Model.Vector2D;

public interface MoveValidator {
    Vector2D canMoveHorizontally(Vector2D position);
    boolean canMoveVertically(Vector2D position);
}
