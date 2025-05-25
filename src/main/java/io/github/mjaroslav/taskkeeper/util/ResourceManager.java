package io.github.mjaroslav.taskkeeper.util;

import com.google.gson.Gson;
import io.github.mjaroslav.taskkeeper.lib.Reference;
import net.harawata.appdirs.AppDirs;
import net.harawata.appdirs.AppDirsFactory;
import org.hildan.fxgson.FxGson;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.InputStream;
import java.nio.file.Path;

public class ResourceManager {
    public final Gson FX_GSON = FxGson.coreBuilder().setPrettyPrinting().create();
    private final AppDirs DIRS = AppDirsFactory.getInstance();

    public @NotNull String abs(@NotNull String path) {
        return path.startsWith("/") ? path : "/" + path;
    }

    public @NotNull String fromPackage(@NotNull String packageName) {
        return abs(packageName.replace('.', '/'));
    }

    public @Nullable InputStream getStream(@NotNull String path) {
        return ResourceManager.class.getResourceAsStream(path);
    }

    public @NotNull Path getConfigDir() {
        return Path.of(DIRS.getUserConfigDir(Reference.NAME, null, null));
    }
}
