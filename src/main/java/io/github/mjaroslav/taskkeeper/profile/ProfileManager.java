package io.github.mjaroslav.taskkeeper.profile;

import io.github.mjaroslav.sharedjava.function.LazySupplier;
import io.github.mjaroslav.sharedjava.io.PathFiles;
import io.github.mjaroslav.taskkeeper.configuration.Configuration;
import io.github.mjaroslav.taskkeeper.util.ResourceManager;
import jdk.jshell.spi.ExecutionControl;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ProfileManager {
    private final @NotNull ResourceManager resources;
    private final @NotNull Configuration configuration;

    public final @NotNull LazySupplier<@NotNull Path> path;

    public ProfileManager(@NotNull ResourceManager resources, @NotNull Configuration configuration) {
        this.resources = resources;
        this.configuration = configuration;
        path = new LazySupplier<>(resources::getProfileDir);
    }

    public @NotNull Profile createProfile() {
        throw new IllegalStateException();
    }

    public @NotNull List<@NotNull String> findProfileNames() {
        return PathFiles.list(path.orElseThrow()).filter(Files::isDirectory).map(it -> it.getFileName().toString()).toList();
    }

    public boolean removeProfile(@NotNull String profile) {
        throw new IllegalStateException();
    }

    public @NotNull Profile loadProfile(@NotNull String profile) {
        return new Profile(profile, resources, this);
    }
}
