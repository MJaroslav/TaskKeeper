package io.github.mjaroslav.taskkeeper.ui.controller.dialog;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

public class NewProfileController implements DialogController {
    @Getter
    @Setter
    private @NotNull Stage stage;

    @FXML
    public Button buttonCancel;
    @FXML
    public Button buttonDone;
    @FXML
    public TextField textName;
}
