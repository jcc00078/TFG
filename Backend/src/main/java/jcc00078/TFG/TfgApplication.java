package jcc00078.TFG;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "jcc00078.TFG.entidades")
@EnableJpaRepositories(basePackages ="jcc00078.TFG.repositorios" )

public class TfgApplication {
	public static void main(String[] args) {
            SpringApplication.run(TfgApplication.class, args);
	}

}
