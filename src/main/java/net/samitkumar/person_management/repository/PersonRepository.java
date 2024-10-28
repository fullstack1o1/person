package net.samitkumar.person_management.repository;

import net.samitkumar.person_management.entity.Person;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface PersonRepository extends ListCrudRepository<Person, Long>, ListPagingAndSortingRepository<Person, Long> {
}
