package agh.ics.oop.model;

import java.util.Objects;

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

    public boolean precedes(Vector2D other){
        return x <= other.x && y <= other.y;
    }

    public boolean follows(Vector2D other){
        return x >= other.x && y >= other.y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Vector2D that))return false;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x,y);
    }
}
