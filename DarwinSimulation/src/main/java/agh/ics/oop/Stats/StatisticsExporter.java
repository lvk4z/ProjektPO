package agh.ics.oop.Stats;

import agh.ics.oop.Stats.SimulationStatistics;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class StatisticsExporter {
    private final SimulationStatistics statistics;
    private final ObservableList<List<String>> data;
    private final List<String> csvHeader = Arrays.asList("Day", "TotalAnimals", "TotalPlants", "AverageEnergy", "Average Life Span", "Average Kids Number");

    public StatisticsExporter(SimulationStatistics statistics) {
        this.statistics = statistics;
        this.data = FXCollections.observableArrayList();
    }

    public void exportToCSV() {

        String projectDirectory = System.getProperty("user.dir");
        String filePath = Paths.get(projectDirectory, "data.csv").toString();

        System.out.println("File Path: " + filePath);

        try (FileWriter fileWriter = new FileWriter(filePath);
             CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT.withHeader(csvHeader.toArray(new String[0])))) {
            for (List<String> rowData : data) {
                csvPrinter.printRecord(rowData);
            }

            System.out.println("The statistics have been saved to a CSV file.");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void updateStatistics(){
        data.add(statistics.getStatisticsAsList());
    }

}
