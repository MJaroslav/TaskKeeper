package io.github.mjaroslav.taskkeeper.ui;

import io.github.mjaroslav.taskkeeper.lib.Reference;
import io.github.mjaroslav.taskkeeper.util.Configuration;
import io.github.mjaroslav.taskkeeper.util.ResourceManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@AllArgsConstructor
@Log4j2
public class LayoutManager {
    private final LayoutController NONE = new LayoutController(new AnchorPane(), null);

    private final @NotNull ResourceManager resources;
    private final @NotNull Configuration configuration;

    private final Map<@NotNull LayoutType, Map<@NotNull LayoutIdentifier, @NotNull LayoutController>> cachedVC = new HashMap<>();

    public @NotNull String getLayoutPath(@NotNull LayoutType type, @NotNull String name) {
        return String.format("%s/%s/%s.fxml", Reference.LAYOUT_ROOT, type.prefix, name);
    }

    public @NotNull LayoutController get(@NotNull LayoutIdentifier identifier) {
        return identifier.isCacheable()
            ? cachedVC.computeIfAbsent(identifier.getType(), k -> new HashMap<>()).computeIfAbsent(identifier,
            key -> load(identifier))
            : identifier.isUndefined() ? NONE : load(identifier);

    }

    public @NotNull LayoutController load(@NotNull LayoutIdentifier identifier) {
        val loader = new FXMLLoader();
        loader.setResources(configuration.bundle.get());
        try {
            val stream = resources.getStream(getLayoutPath(identifier.getType(), identifier.getLayout()));
            Objects.requireNonNull(stream);
            return new LayoutController(loader.load(stream), loader.getController());
        } catch (Exception e) {
            log.error("Can't load {} {}", identifier.name(), identifier.getType().name(), e);
            return NONE;
        }
    }

    public void cacheActivities() {
        Arrays.stream(Activity.values()).filter(Activity::isCacheable).forEach(this::get);
        Arrays.stream(Dialog.values()).filter(Dialog::isCacheable).forEach(this::get);
    }
}
