package net.samitkumar.person.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

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
