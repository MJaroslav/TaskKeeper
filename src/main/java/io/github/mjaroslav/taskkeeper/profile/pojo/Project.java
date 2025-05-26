package io.github.mjaroslav.taskkeeper.profile.pojo;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "projects")
public class Project {
    public static final String NAME = "name";

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField(columnName = NAME)
    public String name;

    @ForeignCollectionField
    public ForeignCollection<Task> tasks;
}
