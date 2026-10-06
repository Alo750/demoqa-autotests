package utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public final class AllureEnvironment {

    private AllureEnvironment() {
    }

    public static void write() {

        Path directory =
                Paths.get("target", "allure-results");

        try {

            Files.createDirectories(directory);

            Properties properties = new Properties();

            properties.setProperty(
                    "os.name",
                    System.getProperty("os.name")
            );

            properties.setProperty(
                    "os.version",
                    System.getProperty("os.version")
            );

            properties.setProperty(
                    "java.version",
                    System.getProperty("java.version")
            );

            properties.setProperty(
                    "browser.matrix",
                    "Chrome, Firefox, Edge"
            );

            properties.setProperty(
                    "selenium",
                    "4.22.0"
            );

            properties.setProperty(
                    "testng",
                    "7.10.2"
            );

            properties.setProperty(
                    "driver.manager",
                    "Selenium Manager"
            );

            try (FileOutputStream output =
                         new FileOutputStream(
                                 directory
                                         .resolve("environment.properties")
                                         .toFile()
                         )) {

                properties.store(
                        output,
                        "Allure environment"
                );
            }

        } catch (IOException e) {

            throw new RuntimeException(
                    "Не удалось создать environment.properties",
                    e
            );
        }
    }
}