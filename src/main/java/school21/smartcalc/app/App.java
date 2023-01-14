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

        String url = "http://localhost:" + PORT;
        Runtime rt = Runtime.getRuntime();
        String os = System.getProperty("os.name");
        System.out.println(os);
        if (os.contains("Mac")) {
            rt.exec("open " + url);
        } else if (os.contains("nix") || os.contains("nux")) {
            String[] browsers = { "google-chrome", "firefox", "mozilla", "epiphany", "konqueror",
                    "netscape", "opera", "links", "lynx" };

            StringBuilder cmd = new StringBuilder();
            for (int i = 0; i < browsers.length; i++)
                if(i == 0)
                    cmd.append(String.format(    "%s \"%s\"", browsers[i], url));
                else
                    cmd.append(String.format(" || %s \"%s\"", browsers[i], url));

            rt.exec(new String[] { "sh", "-c", cmd.toString() });
        } else if (os.contains("Win")) {
            rt.exec("rundll32 url.dll,FileProtocolHandler " + url);
        }

        f.setVisible(false);
        f.dispose();
    }
}

