package com.drpicox.ddd;

import com.drpicox.queue.MessageQueue;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
@Import({MessageQueue.class})
public class Application implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    private CounterRepository counterRepository;

    public Application(CounterRepository counterRepository) {
        this.counterRepository = counterRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // ~/test

        // counterRepository.flush();
        help();
        System.out.println("ala3");
        // System.exit(0);
    }

    @Transactional
    public void help() {
        var n = new Counter();
        counterRepository.save(n);
        // counterRepository.flush();

        counterRepository.findAll().forEach(c -> {
            c.increment();
            c.dump();
            // counterRepository.save(c);
        });
    }
}
