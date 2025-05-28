package io.github.mjaroslav.taskkeeper.profile;

import io.github.mjaroslav.sharedjava.function.LazySupplier;
import io.github.mjaroslav.sharedjava.io.PathFiles;
import io.github.mjaroslav.taskkeeper.configuration.Configuration;
import io.github.mjaroslav.taskkeeper.util.ResourceManager;
import lombok.Getter;
import lombok.val;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProfileManager {
    private final @NotNull ResourceManager resources;
    private final @NotNull Configuration configuration;

    @Getter
    private final Set<@NotNull Profile> profiles = new HashSet<>();

    public final @NotNull LazySupplier<@NotNull Path> path;

    public ProfileManager(@NotNull ResourceManager resources, @NotNull Configuration configuration) {
        this.resources = resources;
        this.configuration = configuration;
        path = new LazySupplier<>(resources::getProfileDir);
    }

    public void findProfiles() {
        PathFiles
            .list(path.orElseThrow())
            .filter(it -> PathFiles.isExtension(it, ResourceManager.EXT_DB))
            .map(PathFiles::removeExtension)
            .map(it -> new Profile(it, resources))
            .forEach(profiles::add);
    }

    public @NotNull List<@NotNull String> getProfileNames() {
        return profiles
            .stream()
            .map(Profile::getName)
            .collect(Collectors.toList());
    }

    public int addProfile(@NotNull String name) {
        val dbFile = PathFiles.get(path.orElseThrow()).resolve(String.format("%s.%s", name, ResourceManager.EXT_DB));
        if (Files.isRegularFile(dbFile)) return 1;
        else {
            profiles.add(new Profile(name, resources));
            return 0;
        }
        // TODO: Add move checks
    }
}
