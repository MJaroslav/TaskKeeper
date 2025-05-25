package io.github.mjaroslav.taskkeeper.configuration;

import io.github.mjaroslav.sharedjava.function.LazySupplier;
import io.github.mjaroslav.taskkeeper.util.ResourceManager;
import javafx.beans.value.ChangeListener;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

@Log4j2
public class Configuration {
    private final @NotNull ResourceManager resources;

    @Getter
    private final ConfigurationData instance = new ConfigurationData();
    public final LazySupplier<@NotNull Path> path;
    private final ChangeListener<Object> observer = (observable, oldValue, newValue) -> {
        if (!Objects.equals(oldValue, newValue)) save();
    };

    public final LazySupplier<@NotNull Locale> locale = new LazySupplier<>(
        () -> getInstance().getLocalization().getLocale());
    public final LazySupplier<@NotNull ResourceBundle> bundle = new LazySupplier<>(
        () -> ResourceBundle.getBundle("assets.bundles.lang", locale.orElseThrow()));

    public Configuration(@NotNull ResourceManager resources) {
        this.resources = resources;
        path = new LazySupplier<>(
            () -> resources.getConfigDir().resolve("config.json"));
    }

    public void init() {
        instance.profileProperty().addListener(observer);
        instance.autoRunProperty().addListener(observer);
        instance.localizationProperty().addListener(observer);
    }

    public boolean read() {
        try {
            val path = this.path.orElseThrow().toAbsolutePath();
            if (!Files.exists(path)) {
                if (!Files.exists(path.getParent())) Files.createDirectory(path.getParent());
                Files.writeString(path, resources.FX_GSON.toJson(new ConfigurationData()));
            }
            instance.copyFrom(resources.FX_GSON.fromJson(Files.readString(path), ConfigurationData.class));
            return true;
        } catch (Exception e) {
            log.warn("Configuration can't be read from file {}", path.get(), e);
        }
        return false;
    }

    public boolean save() {
        try {
            Files.writeString(path.orElseThrow(), resources.FX_GSON.toJson(instance));
            return true;
        } catch (Exception e) {
            log.error("Configuration can't be saved to file {}", path.get(), e);
        }
        return false;
    }
}
