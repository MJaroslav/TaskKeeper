package io.github.mjaroslav.taskkeeper.configuration;

import io.github.mjaroslav.sharedjava.function.LazySupplier;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

public enum Localization {
    ENGLISH("en", "US"),
    RUSSIAN("ru", "RU");

    @Getter
    private final @NotNull String language;
    @Getter
    private final @NotNull String country;
    private final @NotNull LazySupplier<Locale> locale;

    Localization(@NotNull String language, @NotNull String country) {
        this.language = language;
        this.country = country;
        locale = new LazySupplier<>(() -> Locale.of(language, country));
    }

    public final LazySupplier<ResourceBundle> bundle = new LazySupplier<>(
        () -> ResourceBundle.getBundle("assets.bundles.lang", getLocale()));

    public @NotNull Locale getLocale() {
        return locale.orElseThrow();
    }

    public @NotNull ResourceBundle getBundle() {
        return bundle.orElseThrow();
    }

    public @NotNull String toString() {
        return getLocale().getDisplayName(getLocale());
    }

    public static @NotNull Localization fromLocale(@NotNull Locale locale) {
        return Arrays.stream(values())
            .filter(localization -> localization.getLocale().equals(locale))
            .findFirst()
            .orElse(ENGLISH);
    }
}
