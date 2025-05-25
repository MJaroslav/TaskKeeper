package io.github.mjaroslav.taskkeeper.profile;

import com.github.artbits.jsqlite.DB;
import io.github.mjaroslav.taskkeeper.profile.pojo.Line;
import io.github.mjaroslav.taskkeeper.profile.pojo.Project;
import io.github.mjaroslav.taskkeeper.profile.pojo.Task;
import io.github.mjaroslav.taskkeeper.util.ResourceManager;
import lombok.Getter;
import lombok.val;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

public class Profile {
    private final @NotNull String name;
    private final @NotNull ResourceManager resources;
    private final @NotNull ProfileManager profiles;
    @Getter
    private final @NotNull DB instance;

    public Profile(@NotNull String name, @NotNull ResourceManager resource, @NotNull ProfileManager profiles) {
        this.name = name;
        this.resources = resource;
        this.profiles = profiles;
        instance = init();
    }


    public @NotNull Path getDBPath() {
        return resources.getProfileDir().resolve(String.format("%s.%s", name, ResourceManager.EXT_DB));
    }

    private @NotNull DB init() {
        val instance = DB.connect(getDBPath().toAbsolutePath().toString());
        instance.tables(Project.class, Task.class, Line.class);
        return instance;
    }
}
