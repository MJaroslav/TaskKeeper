package io.github.mjaroslav.taskkeeper.profile.pojo;

import com.github.artbits.jsqlite.DataSupport;

import java.util.List;
import java.util.function.Consumer;

public class Task extends DataSupport<Task> {
    public String name;
//    public List<Line> records;

    public Task(Consumer<Task> consumer) {
        super(consumer);
    }
}
