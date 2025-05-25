package io.github.mjaroslav.taskkeeper.profile.pojo;

import com.github.artbits.jsqlite.DataSupport;

import java.util.function.Consumer;

public class Line extends DataSupport<Line> {
    public String comment;
//    public Task task;

    public Line(Consumer<Line> consumer) {
        super(consumer);
    }
}
