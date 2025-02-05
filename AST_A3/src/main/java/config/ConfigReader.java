package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

// This class reads values from the config.properties file using Java's Properties class.
public class ConfigReader {
    private static Properties properties;

    // Static block to initialize the Properties object and load the config.properties file
    static {
        loadProperties();
    }

    // Load properties file
    private static void loadProperties() {
        properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/config.properties")) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            // Use a logging framework (like Log4j or SLF4J)
            System.err.println("Failed to load configuration file. " + e.getMessage());
            throw new RuntimeException("Ensure the config.properties file exists and is accessible.", e);
        }
    }


    // Fetch a property value by key
    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Property \"" + key + "\" not found in the configuration file.");
        }
        return value;
    }


    // Reload the properties file (useful if properties are changed at runtime)
    public static void reloadProperties() {
        loadProperties();
    }
}
