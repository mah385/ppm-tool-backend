package com.ppmtool;

import com.ppmtool.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class PpmToolBackendApplication {

    private final ProjectRepository projectRepository;

    @Autowired
    public PpmToolBackendApplication(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    /*@EventListener({ApplicationReadyEvent.class})
    void applicationReadyEvent() {
        System.out.println("Application started ... launching browser now");
        browse("http://www.google.com");
    }

    public static void browse(String url) {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI(url));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        } else {
            Runtime runtime = Runtime.getRuntime();
            try {
//                runtime.exec("rundll32 url.dll,FileProtocolHandler " + url);
                runtime.exec("xdg-open " + url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }*/

    @EventListener({ApplicationReadyEvent.class})
    void applicationReadyEvent() {
        System.err.println("APPLICATION STARTED");

    }

    public static void main(String[] args) {
        SpringApplication.run(PpmToolBackendApplication.class, args);
    }

}
