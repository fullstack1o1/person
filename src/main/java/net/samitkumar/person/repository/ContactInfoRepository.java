package net.samitkumar.person.repository;

import net.samitkumar.person.entity.ContactInfo;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface ContactInfoRepository extends ListCrudRepository<ContactInfo, Long>, ListPagingAndSortingRepository<ContactInfo, Long> {
    Set<ContactInfo> findByPersonId(@Param("personId") Long personId);
}
