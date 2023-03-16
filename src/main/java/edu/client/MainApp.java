package edu.client;

import edu.client.controller.EditBookController;
import edu.client.controller.MainController;
import edu.client.domain.Library;
import edu.client.domain.LibraryFacade;
import edu.client.model.Book;
import edu.client.utils.AlertUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.Getter;

import java.util.Objects;

public class MainApp extends Application {
    private Stage primaryStage;
    @Getter
    private Library library;

    @Override
    public void start(Stage stage) {
        try {
            this.library = new LibraryFacade();
        } catch (Exception e) {
            AlertUtils.showServerNotFoundAlert();
            throw new RuntimeException();
        }
        this.primaryStage = stage;
        initMainWindow();
    }

    public void initMainWindow() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/main.fxml"));
            AnchorPane booksOverview = loader.load();

            MainController controller = loader.getController();
            controller.setFilteredTableBooks(this);

            Scene scene = new Scene(booksOverview);
            primaryStage.setScene(scene);
            primaryStage.initStyle(StageStyle.TRANSPARENT);

            primaryStage.show();
        } catch (Exception e) {
            AlertUtils.showError(e.getMessage(), String.valueOf(e.getCause()));
            e.printStackTrace();
        }
    }

    public boolean showBookEditDialog(Book tempBookObj) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/editor.fxml"));
            AnchorPane page = loader.load();
            Image editIcon = new Image(
                    Objects.requireNonNull(
                            MainApp.class.getResourceAsStream("images/edit-icon.png")));

            Stage editStage = new Stage();
            editStage.setTitle("Редактирование книги");
            editStage.getIcons().add(editIcon);

            editStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            editStage.setScene(scene);

            EditBookController controller = loader.getController();
            controller.setEditStage(editStage);
            controller.setFields(tempBookObj);

            editStage.showAndWait();
            return controller.isSaveClicked();
        } catch (Exception e) {
            AlertUtils.showError(e.getMessage(), String.valueOf(e.getCause()));
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        launch();
    }
}