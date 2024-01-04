package DarwinWorld.GUI;


import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class InputPresenter {
    private List<Stage> openedWindows = new ArrayList<>();
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


    private Map<String, String> getConfigurationDetails() {
        Map<String, String> configDetails = new LinkedHashMap<>();

        configDetails.put("Map's width", widthTextField.getText());
        configDetails.put("Map's height", heightTextField.getText());
        configDetails.put("Initial Number of Plants", initialPlantsTextField.getText());
        configDetails.put("Single plant energy", plantEnergyTextField.getText());
        configDetails.put("Number of plants growing each day", numberOfPlantsGrowingTextField.getText());
        configDetails.put("Plant Development Option", corpsesPlantGrowthButton.isSelected() ? "Life-giving corpses" : "Normal");
        configDetails.put("Initial Number of Animals", initialAnimalsTextField.getText());
        configDetails.put("Initial animals' energy", initialAnimalEnergyTextField.getText());
        configDetails.put("Energy to consider the animal as full and ready to breed", requiredEnergyToBreedTextField.getText());
        configDetails.put("Energy used for reproduction", energyLostToBreedTextField.getText());
        configDetails.put("Min number of mutations", minMutationsTextField.getText());
        configDetails.put("Max number of mutations", maxMutationsTextField.getText());
        configDetails.put("Mutation variant", mutationVariantButton.isSelected() ? "Podmianka" : "Normal");
        configDetails.put("Genome length", genomeLengthTextField.getText());

        return configDetails;
    }
    @FXML
    private void onSimulationStartClicked() {
        Map<String, String> configDetails = getConfigurationDetails();
        openMainWindow(configDetails);
    }

    private void openMainWindow(Map<String, String> configDetails) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("main.fxml"));
            Parent root = loader.load();

            SimulationPresenter simulationPresenter = loader.getController();
            simulationPresenter.initialize(configDetails);

            Stage mainStage = new Stage();


            mainStage.setTitle("Empty Window");
            mainStage.setScene(new Scene(root));
            openedWindows.add(mainStage);
            mainStage.setOnCloseRequest(event -> openedWindows.remove(mainStage));
            mainStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}