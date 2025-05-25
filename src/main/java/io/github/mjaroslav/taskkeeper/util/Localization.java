package io.github.mjaroslav.taskkeeper.util;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

@AllArgsConstructor
public enum Localization {
    ENGLISH("en", "US"),
    RUSSIAN("ru", "RU");

    private final @NotNull String language;
    private final @NotNull String country;

    public @NotNull Locale getLocale() {
        return Locale.of(language, country);
    }

    public @NotNull String toString() {
        return getLocale().getDisplayName();
    }
}
