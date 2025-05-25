package io.github.mjaroslav.taskkeeper.ui;

import javafx.scene.layout.Region;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public record LayoutController(@NotNull Region root,
                               @Nullable Object controller) {
}
