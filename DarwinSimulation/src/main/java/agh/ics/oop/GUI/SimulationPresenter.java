package agh.ics.oop.GUI;

import agh.ics.oop.MapChangeListener;
import agh.ics.oop.Simulation;
import agh.ics.oop.Stats.ChartDrawer;
import agh.ics.oop.model.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import java.util.Objects;

import agh.ics.oop.Stats.SimulationStatistics;

import javafx.scene.text.Text;

public class SimulationPresenter implements MapChangeListener {
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
    private VBox chartBox;

    private Simulation simulation;
    private ChartDrawer chartDrawer;
    private MapDrawer mapDrawer;

    @FXML
    public void initialize(Configurations config) {
        PlantMap plantMap;
        GraveChangeListener graveChangeListener;
        if (Objects.equals(config.getPlantDevelopmentOption(), "Normal")) {
            plantMap = new EquatorPlantMap(config.getMapHeight(), config.getMapWidth(), config.getSinglePlantEnergy());
            graveChangeListener = null;
        } else {
            plantMap = new GravePlantMap(config.getMapHeight(), config.getMapWidth(), config.getSinglePlantEnergy());
            graveChangeListener = (GraveChangeListener) plantMap;
        }
        AnimalMap animalMap = new AnimalMap(config.getMapHeight(), config.getMapWidth(), config.getEnergyToBreed(), config.getEnergyUsedForReproduction(), graveChangeListener);
        WorldMap map = new WorldMap(plantMap, animalMap);
        this.simulation = new Simulation(map, config, this);
        Thread simulationThread = new Thread(simulation);
        simulationThread.start();

        this.mapDrawer = new MapDrawer(config.getMapWidth(), config.getMapHeight());

        chartDrawer = new ChartDrawer();
        VBox chartContainer = chartDrawer.getChartContainer();
        chartBox.getChildren().add(chartContainer);
    }

    @Override
    public void mapChanged(WorldMap worldMap, SimulationStatistics statistics) {
        Platform.runLater(() -> {
            updateStatistics(statistics);
            mapDrawer.drawMap(mapGrid,worldMap);
        });
    }

    @Override
    public void dayPassed(SimulationStatistics statistics) {
        Platform.runLater(() -> {
            chartDrawer.updateChartData(Integer.parseInt(statistics.getCurrentDay()), Integer.parseInt(statistics.getTotalAnimals()), Integer.parseInt(statistics.getTotalPlants()));
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
    @FXML
    public void highlightDominantGenotype() {

    }

    @FXML
    public void highlightPlantFields() {

    }

}