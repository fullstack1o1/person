package net.samitkumar.person.repository;

import net.samitkumar.person.entity.Address;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends ListCrudRepository<Address, Long>, ListPagingAndSortingRepository<Address, Long> {
    List<Address> findByPersonId(@Param("personId") Long personId);
    Optional<Address> findByPersonIdAndId(@Param("personId") Long personId, @Param("id") Long id);
}
