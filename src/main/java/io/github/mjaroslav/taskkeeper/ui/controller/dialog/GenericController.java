package io.github.mjaroslav.taskkeeper.ui.controller.dialog;

import io.github.mjaroslav.taskkeeper.ui.ButtonType;
import io.github.mjaroslav.taskkeeper.ui.controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.BooleanSupplier;

public class GenericController extends Controller {
    @Getter
    @Setter
    private @NotNull Stage stage;

    @Getter
    private @NotNull ButtonType primaryType = ButtonType.NONE;
    @Getter
    private @NotNull ButtonType secondaryType = ButtonType.NONE;

    @FXML
    public Label labelMessage;

    @FXML
    public Button buttonPrimary;
    @FXML
    public Button buttonSecondary;

    @Setter
    private @Nullable BooleanSupplier actionPrimary;

    @Setter
    private @Nullable BooleanSupplier actionSecondary;

    @Getter
    private @NotNull ButtonType lastButton = ButtonType.NO;

    @FXML
    public void onClickSecondary(@NotNull ActionEvent actionEvent) {
        lastButton = ButtonType.NO;
        if (actionSecondary == null || actionSecondary.getAsBoolean()) stage.close();
    }

    @FXML
    public void onClickPrimary(@NotNull ActionEvent actionEvent) {
        lastButton = ButtonType.YES;
        if (actionPrimary == null || actionPrimary.getAsBoolean()) stage.close();
    }

    public void setMessage(@NotNull String message, @NotNull Object @NotNull ... args) {
        if (labelMessage == null) return;
        labelMessage.setText(String.format(message, args));
    }

    private void configureButton(@NotNull ButtonType type, @NotNull Button button) {
        button.setVisible(type != ButtonType.NONE);
        button.setText(type.getText());
    }

    public void configureButtons(@NotNull ButtonType primaryButton, @NotNull ButtonType secondaryButton) {
        this.primaryType = primaryButton;
        configureButton(primaryButton, buttonPrimary);
        this.secondaryType = secondaryButton;
        configureButton(secondaryButton, buttonSecondary);
    }
}
