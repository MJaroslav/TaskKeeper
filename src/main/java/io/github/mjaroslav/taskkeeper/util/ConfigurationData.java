package io.github.mjaroslav.taskkeeper.util;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jetbrains.annotations.NotNull;

public class ConfigurationData {
    private final @NotNull SimpleObjectProperty<@NotNull Localization> localization;
    private final @NotNull SimpleBooleanProperty autoRun;
    private final @NotNull SimpleStringProperty profile;
    private final @NotNull SimpleListProperty<String> profiles;

    public ConfigurationData() {
        localization = new SimpleObjectProperty<>(Localization.ENGLISH);
        autoRun = new SimpleBooleanProperty(false);
        profile = new SimpleStringProperty("");
        profiles = new SimpleListProperty<>(FXCollections.observableArrayList());
    }

    public void copyFrom(@NotNull ConfigurationData another) {
        localization.set(another.localization.get());
        autoRun.set(another.autoRun.get());
        profile.set(another.profile.get());
        profiles.set(another.profiles.get());
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

    public ObservableList<String> getProfiles() {
        return profiles.get();
    }

    public @NotNull SimpleListProperty<String> profilesProperty() {
        return profiles;
    }
    //endregion
}
