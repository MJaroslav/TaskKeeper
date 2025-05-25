package io.github.mjaroslav.taskkeeper;

import atlantafx.base.theme.PrimerDark;
import io.github.mjaroslav.taskkeeper.ui.Activity;
import io.github.mjaroslav.taskkeeper.ui.Dialog;
import io.github.mjaroslav.taskkeeper.ui.LayoutManager;
import io.github.mjaroslav.taskkeeper.ui.controller.activity.ActivityController;
import io.github.mjaroslav.taskkeeper.ui.controller.dialog.DialogController;
import io.github.mjaroslav.taskkeeper.util.Configuration;
import io.github.mjaroslav.taskkeeper.util.ResourceManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.val;
import org.jetbrains.annotations.NotNull;

@Getter
public class TaskKeeper extends Application {
    @Getter
    private static TaskKeeper instance;

    private Stage primaryStage;

    private ResourceManager resources;
    private LayoutManager layouts;
    private Configuration configuration;

    private Activity currentActivity = Activity.NONE;

    @Override
    public void start(@NotNull Stage primaryStage) {
        Application.setUserAgentStylesheet(new PrimerDark().getUserAgentStylesheet());
        instance = this;
        this.primaryStage = primaryStage;
        resources = new ResourceManager();
        configuration = new Configuration(resources);
        layouts = new LayoutManager(resources, configuration);

        configuration.init();
        configuration.read();
        configuration.save();

        layouts.cacheActivities();

        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(400);

        val anchorPane = new AnchorPane();

        val scene = new Scene(anchorPane);

        primaryStage.setScene(scene);

        dialog(Dialog.PROFILE, true);

        if (currentActivity != Activity.NONE)
            primaryStage.show();
    }

    public void switchActivity(@NotNull Activity activity) {
        if (activity == Activity.NONE) {
            // TODO
        } else if (currentActivity == activity) {
            // TODO
        } else {
            val prevActivity = currentActivity;
            var viewController = layouts.get(prevActivity);
            if (viewController.controller() instanceof ActivityController controller)
                controller.onSwitchedTo(activity);
            viewController = layouts.get(activity);
            currentActivity = activity;
            getPrimaryStage().getScene().setRoot(viewController.root());
            if (viewController.controller() instanceof ActivityController controller)
                controller.onSwitchedFrom(prevActivity);
        }
    }

    public boolean alert(@NotNull String message) {
        val alert = new Alert(AlertType.WARNING, message, ButtonType.YES, ButtonType.NO);
        return alert.showAndWait().orElse(ButtonType.YES) == ButtonType.YES;
    }

    public void dialog(@NotNull Dialog dialog, boolean blockAndWait) {
        val stage = new Stage();
        val layoutController = layouts.get(dialog);
        if (layoutController.controller() instanceof DialogController dialogController)
            dialogController.setStage(stage);
        val root = layoutController.root();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        if (blockAndWait) stage.showAndWait();
        else stage.show();
    }

    public static void main(@NotNull String @NotNull ... args) {
        Application.launch(args);
    }
}
