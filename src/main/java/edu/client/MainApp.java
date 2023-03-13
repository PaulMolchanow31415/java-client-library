package edu.client;

import edu.client.controller.MainController;
import edu.client.controller.EditBookController;
import edu.client.domain.Library;
import edu.client.domain.LibraryFacade;
import edu.client.entity.Book;
import edu.client.utils.AlertUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {
    private Stage primaryStage;
    private final Library library = new LibraryFacade();
    private static final FXMLLoader loader = new FXMLLoader();

    public void initRootLayout() {
        try {
            loader.setLocation(MainApp.class.getResource("view/main.fxml"));
            Image appIcon = new Image("images/app-icon.png");
            AnchorPane mainPane = loader.load();
            Scene scene = new Scene(mainPane);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Личная библиотека");
            primaryStage.getIcons().add(appIcon);

            MainController controller = loader.getController();
            controller.setPrimaryStage(primaryStage);
            controller.setLibrary(library);
            controller.setMainApp(this);
            primaryStage.show();
        } catch (Exception e) {
            AlertUtils.showError(e.getMessage(), String.valueOf(e.getCause()));
        }
    }

    public boolean showBookEditDialog(Book bookObj) {
        try {
            loader.setLocation(MainApp.class.getResource("view/editor.fxml"));
            AnchorPane page = loader.load();

            Stage editStage = new Stage();
            editStage.setTitle("Редактирование книги");
            Image editIcon = new Image("images/edit-icon.png");
            editStage.getIcons().add(editIcon);

            editStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            editStage.setScene(scene);

            EditBookController controller = loader.getController();
            controller.setEditStage(editStage);
            controller.setLibrary(library);
            controller.setMainApp(this);
            // fixme
            controller.setFields(bookObj);

            editStage.showAndWait();
            return controller.isSaveClicked();
        } catch (Exception e) {
            AlertUtils.showError(e.getMessage(), String.valueOf(e.getCause()));
            return false;
        }
    }

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        initRootLayout();
    }

    public static void main(String[] args) {
        launch();
    }
}