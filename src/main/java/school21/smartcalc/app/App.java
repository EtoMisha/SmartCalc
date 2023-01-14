package school21.smartcalc.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import school21.smartcalc.viewModel.Controller;

import javax.swing.*;
import java.io.IOException;
import java.util.Collections;

@SpringBootApplication
@ComponentScan(basePackageClasses = Controller.class)
public class App {
    private static final String PORT = "2121";

    public static void main(String[] args) throws IOException {

        System.setProperty("logging.file.name", LoggerCreator.getLogFile());

        JFrame f = new JFrame();
        f.setVisible(true);

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

