package io.github.mjaroslav.taskkeeper.profile;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import io.github.mjaroslav.taskkeeper.profile.pojo.Line;
import io.github.mjaroslav.taskkeeper.profile.pojo.Project;
import io.github.mjaroslav.taskkeeper.profile.pojo.Task;
import io.github.mjaroslav.taskkeeper.util.ResourceManager;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.sql.SQLException;

@EqualsAndHashCode(of = "name")
@Log4j2
public class Profile implements AutoCloseable {
    @Getter
    private final @NotNull String name;
    private final @NotNull ResourceManager resources;

    @Getter
    private boolean initialized;
    private ConnectionSource connection;
    private Dao<Project, Integer> projects;
    private Dao<Task, Integer> tasks;
    private Dao<Line, Integer> lines;

    public Profile(@NotNull String name, @NotNull ResourceManager resource) {
        this.name = name;
        this.resources = resource;
    }

    public @NotNull Path getDBPath() {
        return resources.getProfileDir().resolve(String.format("%s.%s", name, ResourceManager.EXT_DB));
    }

    private void init() throws SQLException {
        connection = new JdbcConnectionSource("jdbc:sqlite:" + getDBPath());

        TableUtils.createTableIfNotExists(connection, Project.class);
        TableUtils.createTableIfNotExists(connection, Task.class);
        TableUtils.createTableIfNotExists(connection, Line.class);

        projects = DaoManager.createDao(connection, Project.class);
        tasks = DaoManager.createDao(connection, Task.class);
        lines = DaoManager.createDao(connection, Line.class);

        initialized = true;
    }


    public @NotNull ConnectionSource getConnection() {
        if (initialized) return connection;
        else throw new IllegalStateException("Trying to get not opened connection of database " + getDBPath());
    }

    public @NotNull Dao<Project, Integer> getProjects() {
        if (initialized) return projects;
        else throw new IllegalStateException("Trying to get DAO of not opened connection of database " + getDBPath());
    }

    public @NotNull Dao<Task, Integer> getTasks() {
        if (initialized) return tasks;
        else throw new IllegalStateException("Trying to get DAO of not opened connection of database " + getDBPath());
    }

    public @NotNull Dao<Line, Integer> getLines() {
        if (initialized) return lines;
        else throw new IllegalStateException("Trying to get DAO of not opened connection of database " + getDBPath());
    }

    @Override
    public void close() {
        try {
            if (isDBOpen()) connection.close();
        } catch (Exception e) {
            log.error("Can't close database connection for {}", getDBPath(), e);
        }
        initialized = false;
    }

    public boolean isDBOpen() {
        return initialized && connection.isOpen("");
    }

    public void onLoad() {
        try {
            init();
        } catch (SQLException e) {
            log.error("Can't create database connection for {}", getDBPath(), e);
        }
    }

    public void onUnload() {
        close();
    }
}
