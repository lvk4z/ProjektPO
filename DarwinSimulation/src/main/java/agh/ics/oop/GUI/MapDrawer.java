package agh.ics.oop.GUI;

import agh.ics.oop.model.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class MapDrawer {
    private final int mapWidth;
    private final int mapHeight;
    private final List<Rectangle> mapCells = new ArrayList<>();

    private final Image grassImage ;
    private final Image animalImage ;
    private final Image defaultImage ;
    private final Image animalEast ;


    private final double squareSize;
    public MapDrawer(int mapWidth, int mapHeight) {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.squareSize = (double) 500 /mapWidth;
        this.grassImage = new Image("/grass.png");
        this.animalImage = new Image("/animal.png");
        this.defaultImage = new Image("/blank.png");
        this.animalEast = new Image("/animal_E.png");
    }

    public void drawMap(GridPane mapGrid, WorldMap worldMap) {
        mapGrid.getChildren().clear();
        mapCells.clear();

        for (int y = 0; y < mapHeight; y++) {
            for (int x = 0; x < mapWidth; x++) {
                ImageView cellImage = getCellImage(worldMap, x, mapHeight - 1 - y);
                mapGrid.add(cellImage, x, y);
            }
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
                    animalView.setFitWidth(squareSize);
                    animalView.setFitHeight(squareSize);
                    return animalView;
                }
            }
        } else if (cellObject instanceof Plant) {
            ImageView plantView = new ImageView(grassImage);
            plantView.setFitWidth(squareSize);
            plantView.setFitHeight(squareSize);
            return plantView;
        }
        ImageView defaultView = new ImageView(defaultImage);
        defaultView.setFitWidth(squareSize);
        defaultView.setFitHeight(squareSize);
        return defaultView;
    }
}
