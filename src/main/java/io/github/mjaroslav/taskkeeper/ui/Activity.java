package io.github.mjaroslav.taskkeeper.ui;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@AllArgsConstructor
@Getter
public enum Activity implements LayoutIdentifier {
    NONE("", UNDEFINED),
    MAIN("main", CACHEABLE);

    private final LayoutType type = LayoutType.ACTIVITY;
    private final @NotNull String layout;
    private final int flags;
}
