package agh.ics.oop.model;

import java.util.Random;

public enum Direction {

    NORTH,
    NORTHEAST,
    EAST,
    SOUTHEAST,
    SOUTH,
    SOUTHWEST,
    WEST,
    NORTHWEST;

    @Override
    public String toString() {
        return switch (this){
            case NORTH -> "North";
            case EAST -> "East";
            case WEST -> "West";
            case SOUTH -> "South";
            case NORTHEAST -> "NorthEast";
            case NORTHWEST -> "NorthWest";
            case SOUTHEAST -> "SouthEast";
            case SOUTHWEST -> "SouthWest";
        };
    }

    public Direction rotate(int rotation){
        Direction[] values = Direction.values();
        return values[(this.ordinal() + rotation) % values.length];
    }

    public Vector2D toUnitVector(){
        return switch (this){
            case NORTH -> new Vector2D(0,1);
            case EAST -> new Vector2D(1,0);
            case WEST -> new Vector2D(-1,0);
            case SOUTH -> new Vector2D(0,-1);
            case NORTHEAST -> new Vector2D(1,1);
            case NORTHWEST -> new Vector2D(-1,1);
            case SOUTHEAST -> new Vector2D(1,-1);
            case SOUTHWEST -> new Vector2D(-1,-1);
        };
    }


}
