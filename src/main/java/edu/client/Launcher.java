package edu.client;

import edu.client.controller.AppController;
import edu.client.controller.EditBookController;
import edu.client.entity.Book;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        this.primaryStage = stage;

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.class.getResource("view/main.fxml"));
        AnchorPane mainPane = loader.load();
        Scene scene = new Scene(mainPane);
        stage.setTitle("Библиотека");
        stage.setScene(scene);

        AppController controller = loader.getController();
        controller.setPrimaryStage(primaryStage);
        stage.show();
    }

    public static void showBookEditDialog(Book bookObj) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("view/editor.fxml"));
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Редактирование книги");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            EditBookController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setLabels(bookObj);

            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}