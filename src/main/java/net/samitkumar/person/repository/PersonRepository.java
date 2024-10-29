package net.samitkumar.person.repository;

import net.samitkumar.person.entity.Person;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface PersonRepository extends ListCrudRepository<Person, Long>, ListPagingAndSortingRepository<Person, Long> {
}
