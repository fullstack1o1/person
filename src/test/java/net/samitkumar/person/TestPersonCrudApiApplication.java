package net.samitkumar.person;

import org.springframework.boot.SpringApplication;

public class TestPersonCrudApiApplication {

	public static void main(String[] args) {
		SpringApplication.from(PersonCrudApiApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
