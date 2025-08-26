package io.github.mjaroslav.taskkeeper.ui;

import io.github.mjaroslav.sharedjava.format.Bits;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

import static io.github.mjaroslav.taskkeeper.ui.LayoutType.*;

@AllArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum LayoutID {
    NONE(UNKNOWN, "", Flags.UNDEFINED),

    ACTIVITY_MAIN(ACTIVITY, "main", Flags.CACHEABLE),

    DIALOG_PROFILE(DIALOG, "profile", Flags.NO_FLAGS),
    DIALOG_NEW_PROFILE(DIALOG, "new_profile", Flags.CACHEABLE),
    DIALOG_GENERIC(DIALOG, "generic", Flags.NO_FLAGS);

    @NotNull LayoutType type;
    @NotNull String layout;
    int flags;

    public boolean isUndefined() {
        return Bits.isMaskAnd(flags, Flags.UNDEFINED);
    }

    public boolean isCacheable() {
        return Bits.isMaskAnd(flags, Flags.CACHEABLE);
    }

    public static void validateType(@NotNull LayoutID layoutID, @NotNull LayoutType type) {
        if (layoutID != NONE && layoutID.type != type)
            throw new IllegalArgumentException(String.format("%s is not %s", layoutID.name(), type.name()));
    }

    @UtilityClass
    @FieldDefaults(level = AccessLevel.PUBLIC)
    public static class Flags {
        int NO_FLAGS = 0x00;
        int UNDEFINED = 0x01;
        int CACHEABLE = 0x10;
    }
}
