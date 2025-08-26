package io.github.mjaroslav.taskkeeper.ui.controller;

import io.github.mjaroslav.taskkeeper.TaskKeeper;
import io.github.mjaroslav.taskkeeper.ui.ButtonType;
import io.github.mjaroslav.taskkeeper.ui.LayoutID;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.BooleanSupplier;

@Setter
@Getter
public class Controller {
    protected @NotNull Stage stage;

    public @NotNull TaskKeeper app() {
        return TaskKeeper.getInstance();
    }

    public @NotNull String getString(@NotNull String key, @NotNull Object @NotNull ... args) {
        return app().getString(key, args);
    }

    public @NotNull ButtonType confirm(@NotNull String message, @Nullable BooleanSupplier yesAction,
                                       @Nullable BooleanSupplier cancelAction, @NotNull Object @NotNull ... args) {
        return app().confirm(message, this, yesAction, cancelAction, args);
    }

    public boolean confirm(@NotNull String message, @NotNull Object @NotNull ... args) {
        return app().confirm(message, this, args);
    }

    public void dialog(@NotNull LayoutID dialog) {
        app().dialog(dialog, this);
    }

    public void switchTo(@NotNull LayoutID activity) {
        app().switchActivity(activity);
    }
}

