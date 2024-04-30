package mk.ukim.finki.labprefixa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class LabPrefixAApplication {

    public static void main(String[] args) {
        SpringApplication.run(LabPrefixAApplication.class, args);
    }

}
