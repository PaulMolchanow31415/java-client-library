package edu.client.properties;

import lombok.Getter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Getter
public class AppProperties {
    private final String FILENAME = "src/main/app.properties";
    private static final AppProperties configFile = new AppProperties();
    private final Properties property = new Properties();
    private String message = "";

    private AppProperties() {
        try(InputStream input = new FileInputStream(FILENAME)){
            property.load(input);
        } catch (Exception e) {
            message = "Не получается открыть конфигурационный файл";
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return property.getProperty(key);
    }

    public static AppProperties getInstance() {
        return configFile;
    }
}
