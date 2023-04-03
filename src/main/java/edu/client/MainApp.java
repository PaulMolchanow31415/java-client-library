package edu.client;

import edu.client.controller.*;
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
import javafx.scene.layout.HBox;
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
            FXMLLoader mainLoader = new FXMLLoader();
            mainLoader.setLocation(getClass().getResource("view/main.fxml"));
            AnchorPane booksOverview = mainLoader.load();

            MainController mainController = mainLoader.getController();
            mainController.setMainApp(this);
            mainController.setLibrary(library);
            mainController.setFilteredTableBooks();

            FXMLLoader searchLoader = new FXMLLoader();
            searchLoader.setLocation(getClass().getResource("view/searchBox.fxml"));
            HBox searchPane = searchLoader.load();
            SearchBoxController searchController = searchLoader.getController();
            searchController.setMainController(mainController);

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
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/bookEditor.fxml"));
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
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/authorEditor.fxml"));
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
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/publisherEditor.fxml"));
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