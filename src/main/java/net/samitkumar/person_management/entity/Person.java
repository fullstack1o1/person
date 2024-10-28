package net.samitkumar.person_management.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.relational.core.mapping.InsertOnlyProperty;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
public class Person {
    @Id
    Long id;
    String firstName;
    String lastName;
    LocalDate birthDate;
    String profilePhotoUrl;
    @MappedCollection(idColumn = "person_id")
    @InsertOnlyProperty
    Set<Address> address;

    @MappedCollection(idColumn = "person_id")
    Set<ContactInfo> contactInfo;

    @MappedCollection(idColumn = "person_id")
    Set<SocialMedia> socialMediaAccount;

    @ReadOnlyProperty
    LocalDateTime createdAt;
    @ReadOnlyProperty
    LocalDateTime updatedAt;
}
