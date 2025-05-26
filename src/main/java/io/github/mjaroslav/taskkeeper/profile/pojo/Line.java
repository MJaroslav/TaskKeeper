package io.github.mjaroslav.taskkeeper.profile.pojo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "lines")
public class Line {
    public static final String NAME = "name";
    public static final String ACCOUNT_ID = "account_id";

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField(columnName = NAME)
    public String name;

    @DatabaseField(foreign = true, columnName = ACCOUNT_ID)
    public Task task;
}
