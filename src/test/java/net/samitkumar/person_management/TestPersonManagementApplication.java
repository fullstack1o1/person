package net.samitkumar.person_management;

import org.springframework.boot.SpringApplication;

public class TestPersonManagementApplication {

	public static void main(String[] args) {
		SpringApplication.from(PersonManagementApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
