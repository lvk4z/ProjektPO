package agh.ics.oop.GUI.Drawing;

import agh.ics.oop.Model.Animals.Animal;
import agh.ics.oop.Model.Vector2D;
import javafx.scene.layout.StackPane;

import java.util.List;

public class Highlighting {
    private final MapDrawer mapDrawer;

    public Highlighting(MapDrawer mapDrawer) {
        this.mapDrawer = mapDrawer;
    }

    public void highlightPreferredGrassPositions(List<Vector2D> preferredPositions) {
        for (Vector2D position : preferredPositions) {
            StackPane cell = mapDrawer.getMapCell(position.getX(), position.getY());
            if (cell != null) {
                cell.setStyle("-fx-border-width: 1px; -fx-border-color: green;");
            }
        }
    }

    public void unHighlightPreferredGrassPositions(List<Vector2D> preferredPositions){
        for (Vector2D position : preferredPositions) {
            StackPane cell = mapDrawer.getMapCell(position.getX(), position.getY());
            if (cell != null) {
                cell.setStyle("-fx-border-width: 1px; -fx-border-color: transparent;");
            }
        }
    }

    public void highlightDominantGenotype(List<Animal> animals, List<Integer> dominantGenotype){
        for (Animal animal : animals) {
            if (animal.getGenes().equals(dominantGenotype)) {
                StackPane cell = mapDrawer.getMapCell(animal.position().getX(), animal.position().getY());
                if (cell != null) {
                    mapDrawer.highlightCell(cell, animal);
                }
            }
        }
    }

    public void unHighlightDominantGenotype(List<Animal> animals, List<Integer> dominantGenotype){
        for (Animal animal : animals) {
            if (animal.getGenes().equals(dominantGenotype)) {
                StackPane cell = mapDrawer.getMapCell(animal.position().getX(), animal.position().getY());
                if (cell != null) {
                    mapDrawer.unHighlightCell(cell, animal);
                }
            }
        }
    }

}
