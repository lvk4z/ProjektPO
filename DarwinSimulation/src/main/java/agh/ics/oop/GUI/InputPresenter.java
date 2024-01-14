package agh.ics.oop.GUI;


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



    private Map<String, String> getConfigurationDetails() throws ValidationException{
        Map<String, String> configDetails = new HashMap<>();

        int mapWidth = Integer.parseInt(widthTextField.getText());
        Validator.validateMapSize(mapWidth);
        configDetails.put("Map's width", widthTextField.getText());

        int mapHeight = Integer.parseInt(heightTextField.getText());
        Validator.validateMapSize(mapHeight);
        configDetails.put("Map's height", heightTextField.getText());

        int plantsCount = Integer.parseInt(initialPlantsTextField.getText());
        Validator.validateInitialPlants(plantsCount);
        configDetails.put("Initial Number of Plants", initialPlantsTextField.getText());

        int plantsEnergy = Integer.parseInt(plantEnergyTextField.getText());
        Validator.validateInitialPlants(plantsEnergy);
        configDetails.put("Single plant energy", plantEnergyTextField.getText());

        int plantsGrowing = Integer.parseInt(numberOfPlantsGrowingTextField.getText());
        Validator.validateNumberOfPlantsGrowing(plantsGrowing);
        configDetails.put("Number of plants growing each day", numberOfPlantsGrowingTextField.getText());

        configDetails.put("Plant Development Option", corpsesPlantGrowthButton.isSelected() ? "Life-giving corpses" : "Normal");

        int animalsCount = Integer.parseInt(initialAnimalsTextField.getText());
        Validator.validateInitialAnimals(animalsCount);
        configDetails.put("Initial Number of Animals", initialAnimalsTextField.getText());

        int animalsEnergy = Integer.parseInt(initialAnimalEnergyTextField.getText());
        Validator.validateAnimalEnergyValue(animalsEnergy);
        configDetails.put("Initial animals' energy", initialAnimalEnergyTextField.getText());

        int energyToBreed = Integer.parseInt(requiredEnergyToBreedTextField.getText());
        Validator.validateEnergyToBreed(energyToBreed);
        configDetails.put("Energy to consider the animal as full and ready to breed", requiredEnergyToBreedTextField.getText());

        int energyUsedToReproduction = Integer.parseInt(energyLostToBreedTextField.getText());
        Validator.validateEnergyUsedForReproduction(energyUsedToReproduction);
        configDetails.put("Energy used for reproduction", energyLostToBreedTextField.getText());

        int minMutations = Integer.parseInt(minMutationsTextField.getText());
        int maxMutations = Integer.parseInt(maxMutationsTextField.getText());
        Validator.validateMutationRange(minMutations, maxMutations);
        configDetails.put("Min number of mutations", minMutationsTextField.getText());
        configDetails.put("Max number of mutations", maxMutationsTextField.getText());

        configDetails.put("Mutation variant", mutationVariantButton.isSelected() ? "Podmianka" : "Normal");

        int genomeLength = Integer.parseInt(genomeLengthTextField.getText());
        Validator.validateGenomeLength(genomeLength);
        configDetails.put("Genome length", genomeLengthTextField.getText());

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
}