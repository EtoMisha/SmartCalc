package school21.smartcalc.viewModel;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;

public class CustomLogger {
    private static final String FILENAME = "calc.log";
    private static final CustomLogger logger;

    static {
        try {
            logger = new CustomLogger();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private final PrintWriter writer;

    public CustomLogger() throws IOException {
        String logsDirPath = System.getProperty("user.home") + "/SmartCalcFiles/logs/";
        Files.createDirectories(Paths.get(logsDirPath));
        writer = new PrintWriter(Files.newOutputStream(Paths.get(logsDirPath + FILENAME)));
    }

    public static CustomLogger getLogger() {
        return logger;
    }

    public void info(String message) {
        writer.println("[" + LocalDateTime.now() + "] INFO " + message);
    }

    public void error(String message) {
        writer.println("[" + LocalDateTime.now() + "] ERROR " + message);
    }
}
