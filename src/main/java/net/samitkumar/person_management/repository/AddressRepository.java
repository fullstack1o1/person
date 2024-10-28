package net.samitkumar.person_management.repository;

import net.samitkumar.person_management.entity.Address;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface AddressRepository extends ListCrudRepository<Address, Long>, ListPagingAndSortingRepository<Address, Long> {
    Set<Address> findByPersonId(@Param("personId") Long personId);
}
