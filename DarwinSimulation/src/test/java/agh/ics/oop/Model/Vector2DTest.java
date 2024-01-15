package agh.ics.oop.Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2DTest {

    @Test
    void add() {
        Vector2D vector1 = new Vector2D(3,7);
        Vector2D vector2 = new Vector2D(-5,6);
        assertEquals(vector1.add(vector2),new Vector2D(-2,13));
    }

    @Test
    void reverse() {
        Vector2D vector = new Vector2D(-7,8);
        assertEquals(vector.reverse(),new Vector2D(7,-8));
    }
}