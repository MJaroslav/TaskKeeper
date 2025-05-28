package io.github.mjaroslav.taskkeeper.profile.pojo;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Getter
@NoArgsConstructor
@DatabaseTable(tableName = "tasks")
public class Task {
    public static final String NAME = "name";
    public static final String PROJECT_ID = "project_id";

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField(columnName = NAME, canBeNull = false, unique = true)
    private String name;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = PROJECT_ID, canBeNull = false)
    private Project project;

    @ForeignCollectionField
    public ForeignCollection<Line> lines;

    public Task(@NotNull String name, @NotNull Project project) {
        this.name = name;
        this.project = project;
    }
}
