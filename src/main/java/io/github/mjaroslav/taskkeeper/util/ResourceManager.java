package io.github.mjaroslav.taskkeeper.util;

import io.github.mjaroslav.sharedjava.function.LazySupplier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.InputStream;
import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceManager {
    public final LazySupplier<ResourceBundle> bundle = new LazySupplier<>(
        () -> ResourceBundle.getBundle("assets.bundles.lang", Locale.of("en", "US")));

    public @NotNull String abs(@NotNull String path) {
        return path.startsWith("/") ? path : "/" + path;
    }

    public @NotNull String fromPackage(@NotNull String packageName) {
        return abs(packageName.replace('.', '/'));
    }

    public @Nullable InputStream getStream(@NotNull String path) {
        return ResourceManager.class.getResourceAsStream(path);
    }
}
