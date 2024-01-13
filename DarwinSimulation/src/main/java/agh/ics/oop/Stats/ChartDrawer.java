package agh.ics.oop.Stats;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Path;
import javafx.scene.shape.StrokeLineJoin;

import java.util.List;

public class ChartDrawer {
    private final LineChart<Number, Number> lineChart;
    private final NumberAxis xAxis;
    private final NumberAxis yAxis;
    private final XYChart.Series<Number, Number> animalsSeries;
    private final XYChart.Series<Number, Number> plantsSeries;
    private static final int MAX_DATA_POINTS = 10;

    public ChartDrawer() {
        xAxis = new NumberAxis();
        yAxis = new NumberAxis();
        xAxis.setLabel("Day");
        yAxis.setLabel("Count");

        xAxis.setForceZeroInRange(false);
        xAxis.setAutoRanging(false);
        xAxis.setLowerBound(0);
        xAxis.setUpperBound(MAX_DATA_POINTS);
        xAxis.setTickUnit(1);

        lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setMaxWidth(600);
        lineChart.setMaxHeight(400);
        lineChart.setAnimated(false);

        animalsSeries = new XYChart.Series<>();
        animalsSeries.setName("Animals");

        plantsSeries = new XYChart.Series<>();
        plantsSeries.setName("Plants");

        lineChart.getData().addAll(animalsSeries, plantsSeries);
        styleSeries();
    }

    private void styleSeries() {
        animalsSeries.getNode().setStyle("-fx-stroke: red; -fx-stroke-width: 2px; ");
        plantsSeries.getNode().setStyle("-fx-stroke: green; -fx-stroke-width: 2px; ");
    }

    public void updateChartData(int currentDay, int totalAnimals, int totalPlants) {
        animalsSeries.getData().add(new XYChart.Data<>(currentDay, totalAnimals));
        plantsSeries.getData().add(new XYChart.Data<>(currentDay, totalPlants));

        if (animalsSeries.getData().size() > MAX_DATA_POINTS) {
            animalsSeries.getData().remove(0);
            plantsSeries.getData().remove(0);
        }

        xAxis.setLowerBound(currentDay - MAX_DATA_POINTS + 1);
        xAxis.setUpperBound(currentDay + 1);

    }
    public VBox getChartContainer() {
        return new VBox(lineChart);
    }
}
