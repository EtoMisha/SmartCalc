package school21.smartcalc.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import school21.smartcalc.viewModel.Controller;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;

@SpringBootApplication
@ComponentScan(basePackageClasses = Controller.class)
public class App {
    private static final String PORT = "2121";
    private static final String LOGS_PATH = "/SmartCalcFiles/logs/";

    public static void main(String[] args) throws IOException {

        JFrame f = new JFrame();
        f.setVisible(true);

        String userHomeDir = System.getProperty("user.home");
        Files.createDirectories(Paths.get(userHomeDir + LOGS_PATH));

        SpringApplication app = new SpringApplication(App.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", PORT));
        app.run();

        Runtime rt = Runtime.getRuntime();
        String url = "http://localhost:" + PORT;
        rt.exec("open " + url);

        f.setVisible(false);
        f.dispose();
    }
}

