package school21.smartcalc.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import school21.smartcalc.viewModel.CalcController;
import school21.smartcalc.viewModel.CalcLogger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootApplication
@ComponentScan(basePackageClasses = CalcController.class)
public class App {
    public static void main(String[] args) throws IOException {

//        String logsDirPath = System.getProperty("/Users/fbeatris/SmartCalcFiles/logs/");
        Files.createDirectories(Paths.get("/Users/fbeatris/SmartCalcFiles/logs/"));

        SpringApplication.run(App.class, args);


        Runtime rt = Runtime.getRuntime();
        String url = "http://localhost:8080";
        rt.exec("open " + url);
    }
}