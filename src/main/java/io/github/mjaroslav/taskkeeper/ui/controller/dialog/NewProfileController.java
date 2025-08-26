package io.github.mjaroslav.taskkeeper.ui.controller.dialog;

import io.github.mjaroslav.taskkeeper.ui.controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.jetbrains.annotations.NotNull;

public class NewProfileController extends Controller {
    @FXML
    public Button buttonCancel;
    @FXML
    public Button buttonDone;
    @FXML
    public TextField textName;

    @FXML
    public void initialize() {

    }

    @FXML
    public void onClickCancel(@NotNull ActionEvent actionEvent) {
    }

    @FXML
    public void onClickDone(@NotNull ActionEvent actionEvent) {

    }
}
