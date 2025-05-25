package io.github.mjaroslav.taskkeeper.ui.controller.dialog;

import io.github.mjaroslav.taskkeeper.util.Localization;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

import java.util.ResourceBundle;

public class ProfileController {
    @FXML
    public ChoiceBox<String> choiceProfile;
    @FXML
    public ChoiceBox<Localization> choiceLang;

    @FXML
    public void initialize() {
        ResourceBundle.getBundle("assets.bundles.lang");
        choiceLang.setItems(FXCollections.observableArrayList(Localization.values()));
    }
}
