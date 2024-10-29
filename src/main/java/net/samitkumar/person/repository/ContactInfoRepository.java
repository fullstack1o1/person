package net.samitkumar.person.repository;

import net.samitkumar.person.entity.ContactInfo;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ContactInfoRepository extends ListCrudRepository<ContactInfo, Long>, ListPagingAndSortingRepository<ContactInfo, Long> {
    List<ContactInfo> findByPersonId(@Param("personId") Long personId);
    Optional<ContactInfo> findByPersonIdAndId(@Param("personId") Long personId, @Param("id") Long id);
}
