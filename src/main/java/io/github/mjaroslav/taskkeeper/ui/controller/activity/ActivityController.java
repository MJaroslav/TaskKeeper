package io.github.mjaroslav.taskkeeper.ui.controller.activity;

import io.github.mjaroslav.taskkeeper.TaskKeeper;
import io.github.mjaroslav.taskkeeper.ui.Activity;
import org.jetbrains.annotations.NotNull;

public interface ActivityController {
    default void onSwitchedFrom(@NotNull Activity prevActivity) {
    }

    default void onSwitchedTo(@NotNull Activity newActivity) {
    }

    default boolean alert(@NotNull String message) {
        return TaskKeeper.getInstance().alert(message);
    }

    default void switchTo(@NotNull Activity newActivity) {
        TaskKeeper.getInstance().switchActivity(newActivity);
    }
}
