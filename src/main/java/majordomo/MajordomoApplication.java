package majordomo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:spring/users.xml")
public class MajordomoApplication {
    public static void main(String[] args) {
        SpringApplication.run(MajordomoApplication.class);
    }
}
