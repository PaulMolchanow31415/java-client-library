module edu.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires okhttp3;
    requires com.google.gson;
    requires org.kordamp.bootstrapfx.core;

    exports edu.client;
    exports edu.client.controller;
    exports edu.client.entity;
    exports edu.client.response;
    exports edu.client.service;
    exports edu.client.utils;
    opens edu.client to javafx.fxml;
    opens edu.client.controller to javafx.fxml;
}