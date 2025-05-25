package io.github.mjaroslav.taskkeeper.ui.controller.dialog;

import io.github.mjaroslav.taskkeeper.TaskKeeper;
import io.github.mjaroslav.taskkeeper.configuration.Localization;
import io.github.mjaroslav.taskkeeper.ui.Activity;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import lombok.val;
import org.jetbrains.annotations.NotNull;

public class ProfileController implements DialogController {
    @Getter
    @Setter
    private @NotNull Stage stage;

    @FXML
    public ChoiceBox<String> choiceProfile;
    @FXML
    public ChoiceBox<Localization> choiceLang;
    @FXML
    public CheckBox checkAutoRun;
    @FXML
    public Label labelRestart;
    @FXML
    public Button buttonRun;
    @FXML
    public Button buttonRemove;
    @FXML
    public Button buttonAdd;

    @FXML
    public void initialize() {
        val app = TaskKeeper.getInstance();
        val configuration = app.getConfiguration().getInstance();

        choiceLang.setItems(FXCollections.observableArrayList(Localization.values()));
        choiceLang.setValue(configuration.getLocalization());
        configuration.localizationProperty().bind(choiceLang.valueProperty());
        choiceLang.valueProperty().addListener(
            (observable, oldValue, newValue) -> labelRestart.setVisible(newValue != Localization.fromLocale(app.getConfiguration().locale.orElseThrow())));

        checkAutoRun.setSelected(configuration.isAutoRun());
        configuration.autoRunProperty().bind(checkAutoRun.selectedProperty());

        choiceProfile.setItems(FXCollections.observableList(app.getProfiles().findProfileNames()));
        choiceProfile.setValue(configuration.getProfile());
        configuration.profileProperty().bind(choiceProfile.valueProperty());
    }

    @FXML
    public void onButtonRunClick(@NotNull ActionEvent actionEvent) {
        TaskKeeper.getInstance().switchActivity(Activity.MAIN);
        getStage().close();
    }
}
