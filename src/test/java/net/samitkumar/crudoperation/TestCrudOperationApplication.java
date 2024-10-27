package net.samitkumar.crudoperation;

import org.springframework.boot.SpringApplication;

public class TestCrudOperationApplication {

	public static void main(String[] args) {
		SpringApplication.from(CrudOperationApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
