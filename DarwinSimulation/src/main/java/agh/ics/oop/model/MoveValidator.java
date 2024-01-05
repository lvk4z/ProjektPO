package agh.ics.oop.model;

public interface MoveValidator {
    Vector2D canMoveHorizontally(Vector2D position);
    boolean canMoveVertically(Vector2D position);
}
