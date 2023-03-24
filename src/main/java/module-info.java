module edu.client {
    requires org.kordamp.bootstrapfx.core;
    requires javafx.controls;
    requires javafx.fxml;

    requires com.google.gson;
    requires okhttp3;
    requires static lombok;
    requires annotations;
    requires static org.apache.commons.lang3;

    opens edu.client to javafx.fxml;
    opens edu.client.model to com.google.gson;
    opens edu.client.controller to javafx.fxml;

    exports edu.client;
    exports edu.client.model;
    exports edu.client.controller;
    exports edu.client.utils;
    exports edu.client.client;
}