package edu.client.properties;

import lombok.Getter;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

@Getter
public class AppProperties {
    private static final String FILENAME = "src/main/resources/app.properties";
    private static final AppProperties configFile = new AppProperties();
    private static final Properties property = new Properties();

    private AppProperties() {
        try(InputStream input = new FileInputStream(FILENAME)){
            property.load(input);
        } catch (Exception e) {
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
