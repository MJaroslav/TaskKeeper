package io.github.mjaroslav.taskkeeper.profile.pojo;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "tasks")
public class Task {
    public static final String NAME = "name";
    public static final String PROJECT_ID = "project_id";

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField(columnName = NAME)
    public String name;

    @DatabaseField(foreign = true, columnName = PROJECT_ID)
    public Project project;

    @ForeignCollectionField
    public ForeignCollection<Line> lines;
}
