package agh.ics.oop.Model.Plants;

import agh.ics.oop.Model.Vector2D;
import agh.ics.oop.Model.WorldElement;

public record Plant(Vector2D position, int energy) implements WorldElement {}
