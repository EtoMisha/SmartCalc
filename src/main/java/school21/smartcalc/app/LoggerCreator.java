package school21.smartcalc.app;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LoggerCreator {
    private static final String DIR_PATH = "/SmartCalcFiles/";
    private static final String LOGS_PATH = "logs/";
    private static final String PROPERTIES_FILE = "config.properties";
    private static final String USER_HOME_DIR = System.getProperty("user.home");

    public static String getLogFile() {
        try (FileInputStream input = new FileInputStream(USER_HOME_DIR + DIR_PATH + PROPERTIES_FILE)) {
            Properties prop = new Properties();
            prop.load(input);
            int rotationDays = Integer.parseInt(prop.getProperty("logging.rotation.days"));

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy-HH-mm-ss");
            Set<String> filesList = getFilesInDir(USER_HOME_DIR + DIR_PATH + LOGS_PATH + "/");
            String actualFileName = "";
            if (filesList != null) {
                LocalDateTime lastDate = null;
                for (String filename : filesList) {
                    LocalDateTime date =  LocalDateTime.parse(filename.substring(5), formatter);
                    if (lastDate == null || date.isAfter(lastDate)) {
                        lastDate = date;
                        actualFileName = filename;
                    }
                }

                if (lastDate != null && lastDate.plusDays(rotationDays).isBefore(LocalDateTime.now())) {
                    actualFileName = "logs_" + LocalDateTime.now().format(formatter);
                }
            } else {
                actualFileName = "logs_" + LocalDateTime.now().format(formatter);
            }

            return USER_HOME_DIR + DIR_PATH + LOGS_PATH + actualFileName;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Set<String> getFilesInDir(String dir) {
        try (Stream<Path> stream = Files.list(Paths.get(dir))) {
            return stream
                    .filter(file -> !Files.isDirectory(file))
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            return null;
        }
    }
}
