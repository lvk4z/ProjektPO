package agh.ics.oop.GUI.Drawing;


import agh.ics.oop.GUI.Simulation.Configurations;
import agh.ics.oop.GUI.Simulation.ValidationException;
import agh.ics.oop.GUI.Simulation.Validator;
import javafx.application.Platform;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.stage.Stage;

import java.util.*;

public class InputPresenter {
    private final List<Stage> openedWindows = new ArrayList<>();
    @FXML
    private TextField widthTextField;

    @FXML
    private TextField heightTextField;

    @FXML
    private TextField initialPlantsTextField;

    @FXML
    private TextField plantEnergyTextField;

    @FXML
    private TextField numberOfPlantsGrowingTextField;

    @FXML
    private CheckBox corpsesPlantGrowthButton;

    @FXML
    private TextField initialAnimalsTextField;

    @FXML
    private TextField initialAnimalEnergyTextField;

    @FXML
    private TextField requiredEnergyToBreedTextField;

    @FXML
    private TextField energyLostToBreedTextField;

    @FXML
    private TextField minMutationsTextField;

    @FXML
    private TextField maxMutationsTextField;

    @FXML
    private CheckBox mutationVariantButton;

    @FXML
    private TextField genomeLengthTextField;
    @FXML
    private CheckBox exportCSV;


    private Map<String, String> getConfigurationDetails() throws ValidationException {
        Map<String, String> configDetails = new HashMap<>();

        Validator.validateMapSize(widthTextField.getText());
        configDetails.put("Map's width", widthTextField.getText());

        Validator.validateMapSize(heightTextField.getText());
        configDetails.put("Map's height", heightTextField.getText());

        Validator.validateInitialPlants(initialPlantsTextField.getText());
        configDetails.put("Initial Number of Plants", initialPlantsTextField.getText());

        Validator.validateEnergyValue(plantEnergyTextField.getText());
        configDetails.put("Single plant energy", plantEnergyTextField.getText());

        Validator.validateNumberOfPlantsGrowing(numberOfPlantsGrowingTextField.getText());
        configDetails.put("Number of plants growing each day", numberOfPlantsGrowingTextField.getText());

        configDetails.put("Plant Development Option", corpsesPlantGrowthButton.isSelected() ? "Life-giving corpses" : "Normal");

        Validator.validateInitialAnimals(initialAnimalsTextField.getText());
        configDetails.put("Initial Number of Animals", initialAnimalsTextField.getText());

        Validator.validateEnergyValue(initialAnimalEnergyTextField.getText());
        configDetails.put("Initial animals' energy", initialAnimalEnergyTextField.getText());

        Validator.validateEnergyToBreed(requiredEnergyToBreedTextField.getText());
        configDetails.put("Energy to consider the animal as full and ready to breed", requiredEnergyToBreedTextField.getText());

        Validator.validateEnergyUsedForReproduction(energyLostToBreedTextField.getText());
        configDetails.put("Energy used for reproduction", energyLostToBreedTextField.getText());


        Validator.validateMutationRange(minMutationsTextField.getText(), maxMutationsTextField.getText());
        configDetails.put("Min number of mutations", minMutationsTextField.getText());
        configDetails.put("Max number of mutations", maxMutationsTextField.getText());

        configDetails.put("Mutation variant", mutationVariantButton.isSelected() ? "Swap" : "Normal");

        Validator.validateGenomeLength(genomeLengthTextField.getText());
        configDetails.put("Genome length", genomeLengthTextField.getText());

        configDetails.put("Export", exportCSV.isSelected() ? "Export" : "NotExport");

        return configDetails;
    }

    @FXML
    private void onSimulationStartClicked() {
        try {
            Map<String, String> configDetails = getConfigurationDetails();
            Configurations config = new Configurations(configDetails);
            openMainWindow(config);
        } catch (ValidationException e) {
            showAlert(e.getMessage());
        }
    }

    private void openMainWindow(Configurations config) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("main.fxml"));
            Parent root = loader.load();

            SimulationPresenter simulationPresenter = loader.getController();
            Platform.runLater(() -> {
                simulationPresenter.initialize(config);

            });

            Stage mainStage = new Stage();


            var scene = new Scene(root);
            String css = Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm();
            scene.getStylesheets().add(css);
            mainStage.setTitle("Simulation");
            mainStage.setScene(scene);
            openedWindows.add(mainStage);
            mainStage.setOnCloseRequest(event -> {
                openedWindows.remove(mainStage);
                simulationPresenter.stopSimulation();
            });
            mainStage.showAndWait();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Validation Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void onTest1Clicked() {
        widthTextField.setText("10");
        heightTextField.setText("10");
        initialPlantsTextField.setText("8");
        plantEnergyTextField.setText("30");
        numberOfPlantsGrowingTextField.setText("3");
        corpsesPlantGrowthButton.setSelected(true);
        initialAnimalsTextField.setText("12");
        initialAnimalEnergyTextField.setText("105");
        requiredEnergyToBreedTextField.setText("40");
        energyLostToBreedTextField.setText("25");
        minMutationsTextField.setText("8");
        maxMutationsTextField.setText("12");
        genomeLengthTextField.setText("2");
        mutationVariantButton.setSelected(false);
        exportCSV.setSelected(false);
    }

    @FXML
    private void onTest2Clicked() {
        widthTextField.setText("50");
        heightTextField.setText("50");
        initialPlantsTextField.setText("18");
        initialAnimalsTextField.setText("15");
        plantEnergyTextField.setText("50");
        corpsesPlantGrowthButton.setSelected(false);
        numberOfPlantsGrowingTextField.setText("9");
        initialAnimalEnergyTextField.setText("90");
        requiredEnergyToBreedTextField.setText("12");
        energyLostToBreedTextField.setText("10");
        minMutationsTextField.setText("2");
        maxMutationsTextField.setText("10");
        genomeLengthTextField.setText("4");
        mutationVariantButton.setSelected(true);
        exportCSV.setSelected(true);
    }
}