package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    static {
        try {
            // Load the config.properties file
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/config.properties");
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load configuration file.");
        }
    }

    // Method to fetch property values
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
