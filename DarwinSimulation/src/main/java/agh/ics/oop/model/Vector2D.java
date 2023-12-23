package agh.ics.oop.model;

import java.util.Objects;
import java.util.Vector;

public class Vector2D {

    private final int x;
    private final int y;

    public Vector2D(int x,int y) {
        this.x=x;
        this.y=y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Vector2D add(Vector2D other){
        return new Vector2D(x+other.x,y+other.y);
    }

    public Vector2D reverse(){
        return new Vector2D(-x,-y);
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Vector2D))return false;
        Vector2D that = (Vector2D) other;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x,y);
    }
}
