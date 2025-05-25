package io.github.mjaroslav.taskkeeper.profile.pojo;

import com.github.artbits.jsqlite.DataSupport;

import java.util.List;
import java.util.function.Consumer;

public class Project extends DataSupport<Project> {
    public String name;
//    public List<Task> tasks;

    public Project(Consumer<Project> consumer) {
        super(consumer);
    }
}
