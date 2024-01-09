package agh.ics.oop.GUI;

import agh.ics.oop.MapChangeListener;
import agh.ics.oop.Simulation;
import agh.ics.oop.model.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SimulationPresenter implements MapChangeListener {
    @FXML
    private Label configLabel;
    private final List<Rectangle> mapCells = new ArrayList<>();
    @FXML
    private GridPane mapGrid;
    @FXML
    private Label dayLabel;

    @FXML
    private Button stopButton;

    private Simulation simulation;
    private int currentDay = 0;

    @FXML
    public void initialize(Configurations config) {
        PlantMap plantMap;
        GraveChangeListener graveChangeListener;
        if(Objects.equals(config.getPlantDevelopmentOption(), "Normal")) {
             plantMap = new EquatorPlantMap(config.getMapHeight(), config.getMapWidth(), config.getSinglePlantEnergy());
             graveChangeListener = null;
        }else {
             plantMap = new GravePlantMap(config.getMapHeight(), config.getMapWidth(), config.getSinglePlantEnergy());
             graveChangeListener = (GraveChangeListener) plantMap;
        }
        AnimalMap animalMap = new AnimalMap(config.getMapHeight(), config.getMapWidth(), config.getEnergyToBreed(), config.getEnergyUsedForReproduction(), graveChangeListener);
        WorldMap map = new WorldMap(plantMap, animalMap);
        this.simulation = new Simulation(map, config, this);
        Thread simulationThread = new Thread(simulation);
        simulationThread.start();
        drawMap(map);
    }

    @Override
    public void mapChanged(WorldMap worldMap) {
        Platform.runLater(() -> {
            currentDay++;
            updateDayLabel();
            drawMap(worldMap);
        });
    }

    private void drawMap(WorldMap worldMap) {
        mapGrid.getChildren().clear();
        mapCells.clear();

        int mapSize = 30;

        for (int y = 0; y < mapSize; y++) {
            for (int x = 0; x < mapSize; x++) {
                Rectangle cell = new Rectangle(15, 15);
                Paint cellColor = getCellColor(worldMap, x, mapSize - 1 - y);
                cell.setFill(cellColor);
                mapCells.add(cell);
                mapGrid.add(cell, x, y);
            }
        }
    }


    private Paint getCellColor(WorldMap worldMap, int x, int y) {
        Object cellObject = worldMap.objectAt(new Vector2D(x, y));

        if (cellObject instanceof List<?> cellList) {
            if (!cellList.isEmpty()) {
                Object firstObject = cellList.get(0);
                if (firstObject instanceof Animal) {
                    return Color.RED;
                }
            }
        } else if (cellObject instanceof Plant) {
            return Color.GREEN;
        }
        return Color.WHITE;

    }

    @FXML
    public void stopSimulation() {
        simulation.stopSimulation();
        stopButton.setText("Resume");
        stopButton.setOnAction(e -> resumeSimulation());
    }

    @FXML
    public void resumeSimulation() {
        simulation.resumeSimulation();
        stopButton.setText("Stop");
        stopButton.setOnAction(e -> stopSimulation());
    }

    private void updateDayLabel() {
        dayLabel.setText("Day: " + currentDay);
    }
}
