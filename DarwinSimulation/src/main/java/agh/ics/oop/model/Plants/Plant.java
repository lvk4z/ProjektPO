package agh.ics.oop.model.Plants;

import agh.ics.oop.model.Vector2D;
import agh.ics.oop.model.WorldElement;

public record Plant(Vector2D position, int energy) implements WorldElement {}
