module edu.client {
    requires org.kordamp.bootstrapfx.core;
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires com.google.gson;
    requires okhttp3;
    requires static lombok;
    requires annotations;

    opens edu.client to javafx.fxml;
    exports edu.client;
    exports edu.client.entity;
    opens edu.client.entity to com.google.gson;
    exports edu.client.controller;
    opens edu.client.controller to javafx.fxml;
    exports edu.client.utils;
}