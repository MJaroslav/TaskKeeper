package io.github.mjaroslav.taskkeeper.ui;

import io.github.mjaroslav.taskkeeper.TaskKeeper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
public enum ButtonType {
    NONE("unknown"),
    YES("yes"),
    NO("no"),
    CANCEL("cancel"),
    OK("ok");

    @NotNull String key;

    public @NotNull String getText() {
        return TaskKeeper.getInstance().getString("dialog.button." + name().toLowerCase());
    }
}
