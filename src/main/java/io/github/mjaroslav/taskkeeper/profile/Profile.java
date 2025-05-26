package io.github.mjaroslav.taskkeeper.profile;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import io.github.mjaroslav.taskkeeper.profile.pojo.Line;
import io.github.mjaroslav.taskkeeper.profile.pojo.Project;
import io.github.mjaroslav.taskkeeper.profile.pojo.Task;
import io.github.mjaroslav.taskkeeper.util.ResourceManager;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.sql.SQLException;

public class Profile {
    private final @NotNull String name;
    private final @NotNull ResourceManager resources;
    private final @NotNull ProfileManager profiles;
    @Getter
    private ConnectionSource instance;

    public Profile(@NotNull String name, @NotNull ResourceManager resource, @NotNull ProfileManager profiles) {
        this.name = name;
        this.resources = resource;
        this.profiles = profiles;
    }


    public @NotNull Path getDBPath() {
        return resources.getProfileDir().resolve(String.format("%s.%s", name, ResourceManager.EXT_DB));
    }

    public void init() throws SQLException {
        instance = new JdbcConnectionSource("jdbc:sqlite:" + getDBPath());
        TableUtils.createTableIfNotExists(instance, Project.class);
        TableUtils.createTableIfNotExists(instance, Task.class);
        TableUtils.createTableIfNotExists(instance, Line.class);
    }
}
