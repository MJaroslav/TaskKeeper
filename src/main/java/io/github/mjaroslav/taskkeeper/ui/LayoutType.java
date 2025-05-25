package io.github.mjaroslav.taskkeeper.ui;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;

@AllArgsConstructor
public enum LayoutType {
    ACTIVITY("activity"),
    DIALOG("dialog");

    public final @NotNull String prefix;
}
