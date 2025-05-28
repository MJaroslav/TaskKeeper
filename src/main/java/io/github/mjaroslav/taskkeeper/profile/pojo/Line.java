package io.github.mjaroslav.taskkeeper.profile.pojo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Getter
@NoArgsConstructor
@DatabaseTable(tableName = "lines")
public class Line {
    public static final String COMMENT = "comment";
    public static final String ACCOUNT_ID = "account_id";

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField(columnName = COMMENT, canBeNull = false)
    private String comment;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = ACCOUNT_ID, canBeNull = false)
    private Task task;

    public Line(@NotNull String comment, @NotNull Task task) {
        this.comment = comment;
        this.task = task;
    }

    public @NotNull Project getProject() {
        return getTask().getProject();
    }
}
