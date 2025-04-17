package jpa.transactional;

import jpa.transactional.service.TransactionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TransactionalApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransactionalApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            TransactionService transactionService = ctx.getBean(TransactionService.class);

            try {
                transactionService.logWithRequired("Test REQUIRED");
            } catch (Exception e) {
                System.out.println("Caught exception in REQUIRED: " + e.getMessage());
            }

            try {
                transactionService.logWithRequiresNew("Test REQUIRES_NEW");
            } catch (Exception e) {
                System.out.println("Caught exception in REQUIRES_NEW: " + e.getMessage());
            }

            try {
                transactionService.logWithNested("Test NESTED");
            } catch (Exception e) {
                System.out.println("Caught exception in NESTED: " + e.getMessage());
            }
        };
    }
}
