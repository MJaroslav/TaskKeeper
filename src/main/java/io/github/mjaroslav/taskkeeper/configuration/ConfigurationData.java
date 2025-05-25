package io.github.mjaroslav.taskkeeper.configuration;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import org.jetbrains.annotations.NotNull;

public class ConfigurationData {
    private final @NotNull SimpleObjectProperty<@NotNull Localization> localization;
    private final @NotNull SimpleBooleanProperty autoRun;
    private final @NotNull SimpleStringProperty profile;

    public ConfigurationData() {
        localization = new SimpleObjectProperty<>(Localization.ENGLISH);
        autoRun = new SimpleBooleanProperty(false);
        profile = new SimpleStringProperty("");
    }

    public void copyFrom(@NotNull ConfigurationData another) {
        localization.set(another.localization.get());
        autoRun.set(another.autoRun.get());
        profile.set(another.profile.get());
    }

    //region Generated
    public @NotNull Localization getLocalization() {
        return localization.get();
    }

    public @NotNull SimpleObjectProperty<@NotNull Localization> localizationProperty() {
        return localization;
    }

    public boolean isAutoRun() {
        return autoRun.get();
    }

    public @NotNull SimpleBooleanProperty autoRunProperty() {
        return autoRun;
    }

    public String getProfile() {
        return profile.get();
    }

    public @NotNull SimpleStringProperty profileProperty() {
        return profile;
    }
    //endregion
}
