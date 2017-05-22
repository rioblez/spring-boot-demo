package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.github.javafaker.Faker;
import com.example.models.Pie;
import com.example.repositories.PieRepository;

@SpringBootApplication
public class DemoApplication {
	
    private final Faker faker = new Faker();

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
    @Bean
    public CommandLineRunner initializeDb(final PieRepository repository){
        return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
			    repository.deleteAll();
			    //Insert some random pies
			    for(int i = 0; i < 30; i++) {
			        repository.save(new Pie(faker.lorem().word(), faker.lorem().sentence()));
			    }
			}
		};
    }
}
