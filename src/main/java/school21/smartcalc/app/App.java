package school21.smartcalc.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import school21.smartcalc.viewModel.Controller;

@SpringBootApplication
@ComponentScan(basePackageClasses = Controller.class)
public class App {

    public static void main(String[] args) {
        System.setProperty("logging.file.name", LoggerCreator.getLogFile());

        SpringApplication app = new SpringApplication(App.class);
        app.run();
    }
}

