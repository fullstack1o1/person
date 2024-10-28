package net.samitkumar.person_management.model;

import lombok.Builder;
import lombok.Data;
import net.samitkumar.person_management.entity.ContactInfo;

@Data
@Builder
public class ContactInfoRequest {
    String email;
    String phoneNumber;
    ContactInfo.ContactInfoType contactInfoType;
}
