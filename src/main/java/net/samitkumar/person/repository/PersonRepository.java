package net.samitkumar.person.repository;

import net.samitkumar.person.entity.Address;
import net.samitkumar.person.entity.Person;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import java.util.List;

public interface PersonRepository extends ListCrudRepository<Person, Long>, ListPagingAndSortingRepository<Person, Long> {
    List<Person> findPersonByIdIn(List<Long> ids);
    List<Person> findPersonByFirstNameAndLastNameContainingIgnoreCase(String firstName, String lastName);
    List<Person> findPersonByFirstNameOrLastNameContainingIgnoreCase(String firstName, String lastName);
}
