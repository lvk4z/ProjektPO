package agh.ics.oop.GUI.Drawing;

import agh.ics.oop.model.*;
import agh.ics.oop.model.Animals.Animal;
import agh.ics.oop.model.Animals.Direction;
import agh.ics.oop.model.Plants.Plant;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.List;

public class MapDrawer {
    private final int mapWidth;
    private final int mapHeight;
    private final List<StackPane> mapCells = new ArrayList<>();
    private final Image grassImage;
    private final Image animalImage;
    private final Image defaultImage;
    private final Image animalEast;
    private final Image highlightedAnimal;

    private final TrackedAnimalListener trackedAnimalListener;


    private final double squareSize;

    public MapDrawer(int mapWidth, int mapHeight, TrackedAnimalListener trackedAnimalListener) {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.squareSize = (double) 500 / mapWidth;
        this.grassImage = new Image("/grass.png");
        this.animalImage = new Image("/animal.png");
        this.defaultImage = new Image("/blank.png");
        this.animalEast = new Image("/animal_E.png");
        this.highlightedAnimal = new Image("/highlightedAnimal.png");
        this.trackedAnimalListener = trackedAnimalListener;
    }

    public void drawMap(GridPane mapGrid, WorldMap worldMap) {
        mapGrid.getChildren().clear();
        mapCells.clear();

        for (int y = 0; y < mapHeight; y++) {
            for (int x = 0; x < mapWidth; x++) {
                ImageView cellImage = getCellImage(worldMap, x, mapHeight - 1 - y);
                StackPane cellPane = new StackPane();
                cellPane.getChildren().add(cellImage);
                mapCells.add(cellPane);
                mapGrid.add(cellPane, x, y);
            }
        }
    }

    private void handleCellClick(Object animal) {
        if (animal instanceof Animal clickedAnimal) {
            trackedAnimalListener.onAnimalClicked(clickedAnimal);
        }
    }


    private ImageView getCellImage(WorldMap worldMap, int x, int y) {
        Object cellObject = worldMap.getMapInfo().objectAt(new Vector2D(x, y));


        if (cellObject instanceof List<?> cellList) {
            if (!cellList.isEmpty()) {
                Object firstObject = cellList.get(0);
                if (firstObject instanceof Animal) {
                    return getAnimalImageView((Animal) firstObject);
                }
            }
        } else if (cellObject instanceof Plant) {
            ImageView plantView = new ImageView(grassImage);
            plantView.setFitWidth(squareSize - 1);
            plantView.setFitHeight(squareSize - 1);
            return plantView;
        }
        ImageView defaultView = new ImageView(defaultImage);
        defaultView.setFitWidth(squareSize - 1);
        defaultView.setFitHeight(squareSize - 1);
        return defaultView;
    }

    private ImageView getAnimalImageView(Animal animal) {
        Direction direction = animal.getOrientation();

        ImageView animalView;
        if (direction == Direction.EAST) {
            animalView = new ImageView(animalEast);
        } else {
            animalView = new ImageView(animalImage);
        }
        animalView.setFitWidth(squareSize - 1);
        animalView.setFitHeight(squareSize - 1);
        animalView.setOnMouseClicked(event -> handleCellClick(animal));
        return animalView;
    }

    public StackPane getMapCell(int x, int y) {
        int index = (mapHeight - 1 - y) * mapWidth + x;
        if (index >= 0 && index < mapCells.size()) {
            return mapCells.get(index);
        }
        return null;
    }
    public void highlightCell(StackPane cell, Animal animal) {
        if (!cell.getChildren().isEmpty() && cell.getChildren().get(0) instanceof ImageView imageView) {
            imageView.setImage(highlightedAnimal);
            imageView.setOnMouseClicked(event -> handleCellClick(animal));
        }
    }

    public void unHighlightCell(StackPane cell, Animal animal) {
        if (!cell.getChildren().isEmpty() && cell.getChildren().get(0) instanceof ImageView imageView) {
            imageView.setImage(animalImage);
            imageView.setOnMouseClicked(event -> handleCellClick(animal));
        }
    }
}
