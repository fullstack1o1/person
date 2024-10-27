package net.samitkumar.crudoperation.repository;

import net.samitkumar.crudoperation.entity.Person;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface PersonRepository extends ListCrudRepository<Person, Long>, ListPagingAndSortingRepository<Person, Long> { }
