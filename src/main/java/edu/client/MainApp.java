package edu.client;

import edu.client.controller.EditAuthorController;
import edu.client.controller.EditBookController;
import edu.client.controller.MainController;
import edu.client.domain.Library;
import edu.client.domain.LibraryFacade;
import edu.client.model.Author;
import edu.client.model.Book;
import edu.client.utils.AlertUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.Getter;

public class MainApp extends Application {
    private final FXMLLoader loader = new FXMLLoader();
    private Stage primaryStage;
    @Getter
    private Library library;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        initLibrary();
        initMainWindow();
    }

    private void initLibrary() {
        try {
            this.library = new LibraryFacade();
        } catch (Exception e) {
            AlertUtils.showServerNotFoundAlert();
            throw new RuntimeException();
        }
    }

    private Stage createDialogStage(AnchorPane page) {
        Stage editStage = new Stage();
        editStage.initModality(Modality.WINDOW_MODAL);
        editStage.initStyle(StageStyle.TRANSPARENT);
        Scene editScene = new Scene(page);
        editStage.setScene(editScene);
        return editStage;
    }

    /* WINDOWS */
    private void initMainWindow() {
        try {
            loader.setLocation(MainApp.class.getResource("view/main.fxml"));
            AnchorPane booksOverview = loader.load();

            MainController controller = loader.getController();
            controller.setFilteredTableBooks(this);

            Scene mainScene = new Scene(booksOverview);
            primaryStage.setScene(mainScene);
            primaryStage.initStyle(StageStyle.TRANSPARENT);

            primaryStage.show();
        } catch (Exception e) {
            AlertUtils.showError(e.getMessage(), String.valueOf(e.getCause()));
            e.printStackTrace();
        }
    }

    public boolean showBookEditDialog(Book tempBookObj) {
        try {
            loader.setLocation(MainApp.class.getResource("view/bookEditor.fxml"));
            AnchorPane bookEditor = loader.load();

            Stage editStage = createDialogStage(bookEditor);
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

    public void /*fixme*/ showAuthorEditDialog(Author authorObj) {
        try {
            loader.setLocation(MainApp.class.getResource("view/authorEditor.fxml"));
            AnchorPane authorEditor = loader.load();

            Stage editAuthorStage = createDialogStage(authorEditor);
            EditAuthorController authorController = loader.getController();
            authorController.setEditAuthorStage(editAuthorStage);
            authorController.setFields(authorObj);

            editAuthorStage.showAndWait();
            // todo
        } catch (Exception e) {
            AlertUtils.showError(e.getMessage(), String.valueOf(e.getCause()));
            e.printStackTrace();
        }
    }
}