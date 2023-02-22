package edu.client.utils;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class AlertUtils {
    private static Alert alert = new Alert(Alert.AlertType.NONE);
    public void showError(String message) {
        alert.setAlertType(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка заполнения");
        alert.setHeaderText("Пожалуйста, укажите корректные значения текстовых полей");
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void showNothingIsSelectedAlert() {
        alert.setAlertType(Alert.AlertType.WARNING);
        alert.setTitle("Ничего не выбрано");
        alert.setHeaderText("Отсутсвует выбранный пользователь");
        alert.setContentText("Пожалуйста выберите пользователя в таблице");
        alert.showAndWait();
    }

    public void showServerNotFoundAlert(Stage stage) {
        alert.setAlertType(Alert.AlertType.ERROR);
        alert.setTitle("Не удалось подключится к серверу");
        alert.setHeaderText("Критическая ошибка");
        alert.setContentText("Включите сервер и БД, а также перезапустите программу");
        alert.initOwner(stage);
        alert.showAndWait();
    }
}
