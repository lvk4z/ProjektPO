package agh.ics.oop.GUI;

import agh.ics.oop.MapChangeListener;
import agh.ics.oop.Simulation;
import agh.ics.oop.Stats.ChartDrawer;
import agh.ics.oop.model.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

import java.awt.*;
import java.util.List;
import java.util.Objects;

import agh.ics.oop.Stats.SimulationStatistics;

import javafx.scene.text.Text;

public class SimulationPresenter implements MapChangeListener, TrackedAnimalListener{
    @FXML
    private GridPane mapGrid;
    @FXML
    private Text sDay;
    @FXML
    private Text sTotalAnimals;
    @FXML
    private Text sTotalPlants;
    @FXML
    private Text sAverageEnergy;
    @FXML
    private Text sAverageLifeSpan;
    @FXML
    private Text sChildren;

    @FXML
    private Button stopButton;

    @FXML
    private VBox vBoxAnimalInformation;
    @FXML
    private Text positionText;
    @FXML
    private Text energyText;
    @FXML
    private Text genesText;
    @FXML
    private Text nextGenText;
    @FXML
    private Text grassEatenText;
    @FXML
    private Text kidsNumberText;
    @FXML
    private Text daysAliveText;
    @FXML
    private Label statusLabel;
    @FXML
    private VBox chartBox;

    @FXML
    private CheckBox grassCheckBox;

    private Simulation simulation;
    private ChartDrawer chartDrawer;
    private MapDrawer mapDrawer;
    private Animal trackedAnimal = null;
    private List<Vector2D> preferredGrassPositions = null;

    @FXML
    public void initialize(Configurations config) {
        PlantMap plantMap;
        GraveChangeListener graveChangeListener;
        if (Objects.equals(config.getPlantDevelopmentOption(), "Normal")) {
            plantMap = new EquatorPlantMap(config.getMapHeight(), config.getMapWidth(), config.getSinglePlantEnergy());
            graveChangeListener = null;
            preferredGrassPositions = plantMap.getPreferredPositionsList();
        } else {
            plantMap = new GravePlantMap(config.getMapHeight(), config.getMapWidth(), config.getSinglePlantEnergy());
            graveChangeListener = (GraveChangeListener) plantMap;
        }
        AnimalMap animalMap = new AnimalMap(config.getMapHeight(), config.getMapWidth(), config.getEnergyToBreed(), config.getEnergyUsedForReproduction(), graveChangeListener);
        WorldMap map = new WorldMap(plantMap, animalMap);
        this.simulation = new Simulation(map, config, this);
        Thread simulationThread = new Thread(simulation);
        simulationThread.start();

        grassCheckBox.setDisable(true);

        this.mapDrawer = new MapDrawer(config.getMapWidth(), config.getMapHeight(), this);

//        chartDrawer = new ChartDrawer();
//        VBox chartContainer = chartDrawer.getChartContainer();
//        chartBox.getChildren().add(chartContainer);
    }

    @Override
    public void mapChanged(WorldMap worldMap, SimulationStatistics statistics) {
        Platform.runLater(() -> {
            updateStatistics(statistics);
            mapDrawer.drawMap(mapGrid,worldMap);
            if(trackedAnimal != null){
                animalStats();
            }
            else vBoxAnimalInformation.setVisible(false);
            preferredGrassPositions = worldMap.getPreferredGrassPositions();
        });
    }

    @Override
    public void dayPassed(SimulationStatistics statistics) {
        Platform.runLater(() -> {
//            chartDrawer.updateChartData(Integer.parseInt(statistics.getCurrentDay()), Integer.parseInt(statistics.getTotalAnimals()), Integer.parseInt(statistics.getTotalPlants()));
        });
    }

    @FXML
    public void handleGrassCheckBox() {
        Platform.runLater(() -> {
            if (grassCheckBox.isSelected()) {
                for (Vector2D position : preferredGrassPositions) {
                    StackPane cell = mapDrawer.getMapCell(position.getX(), position.getY());
                    if (cell != null) {
                        cell.setStyle("-fx-border-width: 1px; -fx-border-color: green;");
                    }
                }
            } else {
                for (Vector2D position : preferredGrassPositions) {
                    StackPane cell = mapDrawer.getMapCell(position.getX(), position.getY());
                    if (cell != null) {
                        cell.setStyle("-fx-border-width: 1px; -fx-border-color: transparent;");
                    }
                }
            }
        });
    }
    public void updateStatistics(SimulationStatistics statistics) {
        sDay.setText(statistics.getCurrentDay());
        sTotalAnimals.setText(statistics.getTotalAnimals());
        sTotalPlants.setText(statistics.getTotalPlants());
        sAverageEnergy.setText(statistics.getAverageEnergy());
        sAverageLifeSpan.setText(statistics.getAverageLifeSpanForDeadAnimals());
        sChildren.setText(statistics.getAverageNumberOfChildrenForLivingAnimals());
    }

    private void animalStats() {
        positionText.setText(trackedAnimal.position().toString());
        genesText.setText(trackedAnimal.getGenes().toString());
        nextGenText.setText(String.valueOf(trackedAnimal.getNextActiveGen()));
        energyText.setText(String.valueOf(trackedAnimal.energy()));
        grassEatenText.setText(String.valueOf(trackedAnimal.getGrassEaten()));
        kidsNumberText.setText(String.valueOf(trackedAnimal.getKidsNumber()));
        if(!trackedAnimal.isStillAlive()){
            statusLabel.setText("Died at day: ");
            daysAliveText.setText(String.valueOf(trackedAnimal.getDeathDay()));
        }
        else{daysAliveText.setText(String.valueOf(trackedAnimal.getLifetime()));}
    }

    private void clearAnimalStats() {
        trackedAnimal = null;
        positionText.setText("");
        energyText.setText("");
        genesText.setText("");
        nextGenText.setText("");
        grassEatenText.setText("");
        kidsNumberText.setText("");
        daysAliveText.setText("");
    }
    @FXML
    public void stopSimulation() {
        simulation.stopSimulation();
        stopButton.setText("Resume");
        grassCheckBox.setDisable(false);
        stopButton.setOnAction(e -> resumeSimulation());
        clearAnimalStats();
    }

    @FXML
    public void resumeSimulation() {
        simulation.resumeSimulation();
        stopButton.setText("Stop");
        grassCheckBox.setSelected(false);
        grassCheckBox.setDisable(true);
        stopButton.setOnAction(e -> stopSimulation());
    }
    @FXML
    public void highlightDominantGenotype() {

    }

    @Override
    public void onAnimalClicked(Animal animal) {
        trackedAnimal = animal;
        vBoxAnimalInformation.setVisible(true);
        animalStats();
    }
}