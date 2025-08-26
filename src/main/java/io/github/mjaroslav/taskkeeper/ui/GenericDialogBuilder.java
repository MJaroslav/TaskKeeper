package io.github.mjaroslav.taskkeeper.ui;

import io.github.mjaroslav.taskkeeper.TaskKeeper;
import io.github.mjaroslav.taskkeeper.ui.controller.dialog.GenericController;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.BooleanSupplier;

@Getter
public class GenericDialogBuilder {
    private @NotNull String text = "";
    private @NotNull ButtonType primaryType = ButtonType.OK;
    private @NotNull ButtonType secondaryType = ButtonType.NONE;

    private @Nullable BooleanSupplier primaryAction = null;
    private @Nullable BooleanSupplier secondaryAction = null;

    public GenericDialogBuilder translate(@NotNull String key, @NotNull Object @NotNull ... args) {
        return text(TaskKeeper.getInstance().getString(key, args));
    }

    public GenericDialogBuilder text(@NotNull String text, @NotNull Object @NotNull ... args) {
        return text(String.format(text, args));
    }

    public GenericDialogBuilder text(@NotNull String text) {
        this.text = text;
        return this;
    }

    public GenericDialogBuilder primaryButton(@NotNull ButtonType type) {
        return primaryButton(type, null);
    }

    public GenericDialogBuilder primaryButton(@NotNull ButtonType type, @Nullable BooleanSupplier action) {
        primaryType = type;
        primaryAction = action;
        return this;
    }

    public GenericDialogBuilder secondaryButton(@NotNull ButtonType type) {
        return secondaryButton(type, null);
    }

    public GenericDialogBuilder secondaryButton(@NotNull ButtonType type, @Nullable BooleanSupplier action) {
        secondaryType = type;
        secondaryAction = action;
        return this;
    }

    public void configure(@NotNull GenericController controller) {
        controller.setMessage(text);
        controller.configureButtons(primaryType, secondaryType);
        controller.setActionPrimary(primaryAction);
        controller.setActionSecondary(secondaryAction);
    }
}
