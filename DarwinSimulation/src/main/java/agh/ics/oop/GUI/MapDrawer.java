package agh.ics.oop.GUI;

import agh.ics.oop.model.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class MapDrawer {
    private final int mapWidth;
    private final int mapHeight;
    private final List<StackPane> mapCells = new ArrayList<>();
    private final Image grassImage ;
    private final Image animalImage ;
    private final Image defaultImage ;
    private final Image animalEast ;

    private final TrackedAnimalListener trackedAnimalListener;


    private final double squareSize;
    public MapDrawer(int mapWidth, int mapHeight, TrackedAnimalListener trackedAnimalListener) {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.squareSize = (double) 500 /mapWidth;
        this.grassImage = new Image("/grass.png");
        this.animalImage = new Image("/animal.png");
        this.defaultImage = new Image("/blank.png");
        this.animalEast = new Image("/animal_E.png");
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
        Object cellObject = worldMap.objectAt(new Vector2D(x, y));

        if (cellObject instanceof List<?> cellList) {
            if (!cellList.isEmpty()) {
                Object firstObject = cellList.get(0);
                if (firstObject instanceof Animal) {
                    Direction direction = ((Animal) firstObject).getOrientation();

                    ImageView animalView;
                    if (direction == Direction.EAST) {
                        animalView = new ImageView(animalEast);
                    } else {
                        animalView = new ImageView(animalImage);
                    }
                    animalView.setFitWidth(squareSize-1);
                    animalView.setFitHeight(squareSize-1);
                    animalView.setOnMouseClicked(event -> handleCellClick(firstObject));
                    return animalView;
                }
            }
        } else if (cellObject instanceof Plant) {
            ImageView plantView = new ImageView(grassImage);
            plantView.setFitWidth(squareSize-1);
            plantView.setFitHeight(squareSize-1);
            return plantView;
        }
        ImageView defaultView = new ImageView(defaultImage);
        defaultView.setFitWidth(squareSize-1);
        defaultView.setFitHeight(squareSize-1);
        return defaultView;
    }

    public StackPane getMapCell(int x, int y) {
        int index = y * mapWidth + x;
        if (index >= 0 && index < mapCells.size()) {
            return mapCells.get(index);
        }
        return null;
    }
}
