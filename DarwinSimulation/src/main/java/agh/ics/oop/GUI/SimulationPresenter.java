package DarwinWorld.GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.Map;

public class SimulationPresenter {
    @FXML
    private Label configLabel;

    @FXML
    public void initialize(Map<String, String> configDetails) {
        StringBuilder details = new StringBuilder();
        for (Map.Entry<String, String> entry : configDetails.entrySet()) {
            details.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        configLabel.setText(details.toString());
    }
}
