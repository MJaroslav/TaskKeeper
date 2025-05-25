package io.github.mjaroslav.taskkeeper.ui;

import io.github.mjaroslav.sharedjava.format.Bits;
import org.jetbrains.annotations.NotNull;

public interface LayoutIdentifier {
    int NO_FLAGS = 0x00;
    int UNDEFINED = 0x01;
    int CACHEABLE = 0x10;

    @NotNull String name();

    @NotNull String getLayout();

    @NotNull LayoutType getType();

    int getFlags();

    default boolean isUndefined() {
        return Bits.isMaskAnd(getFlags(), UNDEFINED);
    }

    default boolean isCacheable() {
        return Bits.isMaskAnd(getFlags(), CACHEABLE);
    }
}
