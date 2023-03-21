package edu.client.utils;

import javafx.scene.control.Alert;

public class AlertUtils {
    private static final Alert alert = new Alert(Alert.AlertType.NONE);

    public static void showError(String message, String cause) {
        alert.setAlertType(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText(cause);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void showIncorrectFillAlert(String message) {
        alert.setAlertType(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка заполнения");
        alert.setHeaderText("Пожалуйста, укажите корректные значения текстовых полей");
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void showNothingIsSelectedAlert() {
        alert.setAlertType(Alert.AlertType.WARNING);
        alert.setTitle("Ничего не выбрано");
        alert.setHeaderText("Отсутсвует выбранная книга");
        alert.setContentText("Пожалуйста выберите книгу в таблице");
        alert.showAndWait();
    }

    public static void showNotExistingItemAlert() {
        alert.setAlertType(Alert.AlertType.WARNING);
        alert.setTitle("Элемент не найден");
        alert.setHeaderText("Данный элемент отсутсвует");
        alert.setContentText("Выбранная книга не имеет автора или названия");
        alert.showAndWait();
    }

    public static void showServerNotFoundAlert() {
        alert.setAlertType(Alert.AlertType.ERROR);
        alert.setTitle("Не удалось подключится к серверу");
        alert.setHeaderText("Критическая ошибка");
        alert.setContentText("Включите сервер и БД, а также перезапустите программу");
        alert.showAndWait();
    }
}
