package edu.client;

import edu.client.controller.EditAuthorController;
import edu.client.controller.EditBookController;
import edu.client.controller.EditPublisherController;
import edu.client.controller.MainController;
import edu.client.domain.Library;
import edu.client.domain.LibraryFacade;
import edu.client.model.Author;
import edu.client.model.Book;
import edu.client.model.Publisher;
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view/main.fxml"));
            AnchorPane layout = loader.load();
            MainController controller = loader.getController();

            controller.setMainApp(this);
            controller.setLibrary(library);
            controller.setFilteredTableBooks();

            Scene mainScene = new Scene(layout);
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view/bookEditor.fxml"));
            AnchorPane bookEditor = loader.load();

            Stage editStage = createDialogStage(bookEditor);
            EditBookController controller = loader.getController();
            controller.setEditStage(editStage);
            controller.setMainApp(this);
            controller.setFields(tempBookObj);

            editStage.showAndWait();
            return controller.isSaveClicked();
        } catch (Exception e) {
            AlertUtils.showError(e.getMessage(), String.valueOf(e.getCause()));
            e.printStackTrace();
            return false;
        }
    }

    /* MICRO AUTHOR / PUBLISHER DIALOGS */
    public boolean showAuthorEditDialog(Author tempAuthorObj) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view/authorEditor.fxml"));
            AnchorPane authorEditor = loader.load();

            Stage editAuthorStage = createDialogStage(authorEditor);
            EditAuthorController authorController = loader.getController();
            authorController.setEditAuthorStage(editAuthorStage);
            authorController.setFields(tempAuthorObj);

            editAuthorStage.showAndWait();
            return authorController.isUpdateClicked();
        } catch (Exception e) {
            AlertUtils.showError(e.getMessage(), String.valueOf(e.getCause()));
            e.printStackTrace();
            return false;
        }
    }

    public boolean showPublisherEditDialog(Publisher tempPublisherObj) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view/publisherEditor.fxml"));
            AnchorPane publisherEditor = loader.load();

            Stage editPublisherStage = createDialogStage(publisherEditor);
            EditPublisherController publisherController = loader.getController();
            publisherController.setEditPublisherStage(editPublisherStage);
            publisherController.setFields(tempPublisherObj);

            editPublisherStage.showAndWait();
            return publisherController.isUpdateClicked();
        } catch (Exception e) {
            AlertUtils.showError(e.getMessage(), String.valueOf(e.getCause()));
            e.printStackTrace();
            return false;
        }
    }
}