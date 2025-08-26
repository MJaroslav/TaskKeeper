package io.github.mjaroslav.taskkeeper;

import atlantafx.base.theme.PrimerDark;
import io.github.mjaroslav.taskkeeper.configuration.Configuration;
import io.github.mjaroslav.taskkeeper.profile.Profile;
import io.github.mjaroslav.taskkeeper.profile.ProfileManager;
import io.github.mjaroslav.taskkeeper.ui.*;
import io.github.mjaroslav.taskkeeper.ui.controller.ActivityController;
import io.github.mjaroslav.taskkeeper.ui.controller.Controller;
import io.github.mjaroslav.taskkeeper.ui.controller.dialog.GenericController;
import io.github.mjaroslav.taskkeeper.util.ResourceManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.BooleanSupplier;

@Log4j2
@Getter
public class TaskKeeper extends Application {
    @Getter
    private static TaskKeeper instance;

    private Stage primaryStage;

    private ResourceManager resources;
    private LayoutManager layouts;
    private Configuration configuration;
    private ProfileManager profiles;

    private LayoutID currentActivity = LayoutID.NONE;

    private Profile currentProfile;

    @Override
    public void start(@NotNull Stage primaryStage) {
        Application.setUserAgentStylesheet(new PrimerDark().getUserAgentStylesheet());
        instance = this;
        this.primaryStage = primaryStage;
        resources = new ResourceManager();
        configuration = new Configuration(resources);
        layouts = new LayoutManager(resources, configuration);
        profiles = new ProfileManager(resources, configuration);
        profiles.findProfiles();

        configuration.init();
        configuration.read();
        configuration.save();

        layouts.cacheActivities();

        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(400);

        val anchorPane = new AnchorPane();

        val scene = new Scene(anchorPane);

        primaryStage.setScene(scene);

        dialog(LayoutID.DIALOG_PROFILE, getPrimaryStage());

        if (currentActivity != LayoutID.NONE) {
            if (profiles.getProfileNames().contains(configuration.getInstance().getProfile())) {
                currentProfile = profiles.getProfiles()
                    .stream()
                    .filter(it -> it.getName().equals(configuration.getInstance().getProfile()))
                    .findFirst().orElseThrow();
                currentProfile.onLoad();
                primaryStage.show();
                Runtime.getRuntime().addShutdownHook(new Thread(() -> profiles.getProfiles().forEach(Profile::onUnload)));
            }
        }
    }

    public void switchActivity(@NotNull LayoutID activity) {
        LayoutID.validateType(activity, LayoutType.ACTIVITY);
        if (activity == LayoutID.NONE)
            log.warn("Trying to switch activity to NONE");
        else if (currentActivity == activity)
            log.warn("Trying to switch activity to same activity");
        else {
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

    public void dialog(@NotNull LayoutID dialog, @Nullable Object owner) {
        LayoutID.validateType(dialog, LayoutType.DIALOG);
        val stage = new Stage();
        val layoutController = layouts.get(dialog);
        if (layoutController.controller() instanceof Controller dialogController)
            dialogController.setStage(stage);
        val root = layoutController.root();
        if (root.getScene() == null) stage.setScene(new Scene(root));
        else stage.setScene(root.getScene());
        stage.setResizable(false);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(owner instanceof Controller controller ? controller.getStage() : primaryStage);
        stage.showAndWait();
    }

    public @NotNull LayoutController generic(@Nullable Object owner, @NotNull GenericDialogBuilder builder) {
        val stage = new Stage();
        val layoutController = layouts.get(LayoutID.DIALOG_GENERIC);
        if (layoutController.controller() instanceof Controller controller)
            controller.setStage(stage);
        if (layoutController.controller() instanceof GenericController genericController)
            builder.configure(genericController);
        val root = layoutController.root();
        if (root.getScene() == null) stage.setScene(new Scene(root));
        else stage.setScene(root.getScene());
        stage.setResizable(false);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(owner instanceof Controller controller ? controller.getStage() : primaryStage);
        return layoutController;
    }

    public boolean confirm(@NotNull String message, @Nullable Object owner, @NotNull Object @NotNull ... args) {
        return confirm(message, owner, null, null, args) == ButtonType.YES;
    }

    public @NotNull ButtonType confirm(@NotNull String message, @Nullable Object owner, @Nullable BooleanSupplier okAction, @Nullable BooleanSupplier cancelAction, @NotNull Object @NotNull ... args) {
        val builder = new GenericDialogBuilder()
            .primaryButton(ButtonType.OK, okAction)
            .secondaryButton(ButtonType.CANCEL, cancelAction)
            .translate(message, args);
        val layoutController = generic(owner, builder);
        if (layoutController.controller() instanceof Controller controller) controller.getStage().showAndWait();
        if (layoutController.controller() instanceof GenericController genericController)
            return genericController.getLastButton();
        return ButtonType.CANCEL;
    }

    public @NotNull String getString(@NotNull String key, @NotNull Object @NotNull ... args) {
        val localized = configuration.bundle.orElseThrow().getString(key);
        return args.length == 0 ? localized : String.format(localized, args);
    }

    public static void main(@NotNull String @NotNull ... args) {
        Application.launch(args);
    }
}
