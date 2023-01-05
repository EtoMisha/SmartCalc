package school21.smartcalc.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import school21.smartcalc.viewModel.CalcController;

import java.io.IOException;

@SpringBootApplication
@ComponentScan(basePackageClasses = CalcController.class)
public class App {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(App.class, args);

        Runtime rt = Runtime.getRuntime();
        String url = "http://localhost:8080";
        rt.exec("open " + url);

    }
}