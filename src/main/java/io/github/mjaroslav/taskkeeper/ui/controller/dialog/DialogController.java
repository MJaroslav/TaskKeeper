package io.github.mjaroslav.taskkeeper.ui.controller.dialog;

import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

public interface DialogController {
    void setStage(@NotNull Stage stage);

    @NotNull Stage getStage();
}
