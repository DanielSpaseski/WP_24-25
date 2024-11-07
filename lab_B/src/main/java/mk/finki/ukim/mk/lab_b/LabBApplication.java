package mk.finki.ukim.mk.lab_b;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


@SpringBootApplication
@ServletComponentScan
public class LabBApplication {

    public static void main(String[] args) {
        SpringApplication.run(LabBApplication.class, args);
    }

}
