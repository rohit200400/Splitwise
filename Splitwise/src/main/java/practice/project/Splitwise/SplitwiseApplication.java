package practice.project.splitwise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import practice.project.splitwise.service.initilazation.InitService;

@SpringBootApplication
public class SplitwiseApplication implements CommandLineRunner {
    @Autowired
    private InitService initService;

    public static void main(String[] args) {
        SpringApplication.run(SplitwiseApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Runner from commandLine");
        //initService.initialise();
        System.out.println("Runner from commandLine finished");
    }
}
