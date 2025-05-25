package io.github.mjaroslav.taskkeeper.ui;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@AllArgsConstructor
@Getter
public enum Dialog implements LayoutIdentifier {
    NONE("", UNDEFINED),
    PROFILE("profile", CACHEABLE);

    private final LayoutType type = LayoutType.DIALOG;
    private final @NotNull String layout;
    private final int flags;
}
