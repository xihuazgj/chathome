package xhu.zgj.chathome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ChathomeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChathomeApplication.class, args);
    }

}
