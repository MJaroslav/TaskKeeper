package io.github.mjaroslav.taskkeeper.profile.pojo;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@NoArgsConstructor
@Getter
@DatabaseTable(tableName = "projects")
public class Project {
    public static final String NAME = "name";

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField(columnName = NAME, canBeNull = false, unique = true)
    private String name;

    @ForeignCollectionField
    private ForeignCollection<Task> tasks;

    public Project(@NotNull String name) {
        this.name = name;
    }
}
