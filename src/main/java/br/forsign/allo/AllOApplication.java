package br.forsign.allo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "br.forsign.allo")
@EnableJpaRepositories(basePackages = "br.forsign.allo", repositoryImplementationPostfix = "CustomImpl")
public class AllOApplication {
	public static void main(String[] args) {
		SpringApplication.run(AllOApplication.class, args);
	}

}
