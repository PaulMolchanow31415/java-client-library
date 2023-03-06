package edu.client;

import edu.client.controller.LauncherController;
import edu.client.controller.EditBookController;
import edu.client.entity.Book;
import edu.client.utils.AlertUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Launcher extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Launcher.class.getResource("view/main.fxml"));
            AnchorPane mainPane = loader.load();
            Scene scene = new Scene(mainPane);
            stage.getIcons().add(new Image("file:app-icon.png"));
            stage.setTitle("Библиотека");
            stage.setScene(scene);

            LauncherController controller = loader.getController();
            controller.setPrimaryStage(primaryStage);
            stage.show();
        } catch (Exception e) {
            AlertUtils.showError(e.getMessage(), String.valueOf(e.getCause()));
            e.printStackTrace();
        }

    }

    public static void showBookEditDialog(Book bookObj) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Launcher.class.getResource("view/editor.fxml"));
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Редактирование книги");
            dialogStage.getIcons().add(new Image("file:edit-icon.png"));
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            EditBookController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setLabels(bookObj);

            dialogStage.showAndWait();
        } catch (Exception e) {
            AlertUtils.showError(e.getMessage(), String.valueOf(e.getCause()));
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}