package net.samitkumar.person_management.repository;

import net.samitkumar.person_management.entity.SocialMedia;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface SocialMediaRepository extends ListCrudRepository<SocialMedia, Long>, ListPagingAndSortingRepository<SocialMedia, Long> {
    Set<SocialMedia> findByPersonId(@Param("personId") Long personId);
}
