package net.samitkumar.person_management.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

@Data
@Builder
public class ContactInfo {
    @Id
    Long id;
    ContactInfoType contactInfoType;
    Long personId; // Foreign Key
    String email;
    String phoneNumber;

    public enum ContactInfoType { PERSONAL, WORK, HOME }
}
