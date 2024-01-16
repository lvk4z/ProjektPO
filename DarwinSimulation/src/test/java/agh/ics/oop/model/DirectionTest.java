package agh.ics.oop.model;

import agh.ics.oop.model.Animals.Direction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DirectionTest {

    @Test
    void rotateWithoutLoop() {
        Direction test = Direction.NORTH;
        Direction result = Direction.SOUTHWEST;
        test = test.rotate(5);
        assertEquals(test,result);
    }

    @Test
    void rotateWithLoop() {
        Direction test = Direction.SOUTH;
        Direction result = Direction.SOUTHEAST;
        test = test.rotate(7);
        assertEquals(test,result);
    }

    @Test
    void toUnitVector() {
        Direction test = Direction.NORTHWEST;
        Vector2D result = new Vector2D(-1,1);
        assertEquals(test.toUnitVector(),result);
    }
}