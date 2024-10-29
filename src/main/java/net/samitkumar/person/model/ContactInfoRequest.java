package net.samitkumar.person.model;

import lombok.Builder;
import lombok.Data;
import net.samitkumar.person.entity.ContactInfo;

@Data
@Builder
public class ContactInfoRequest {
    String email;
    String phoneNumber;
    ContactInfo.ContactInfoType contactInfoType;
}
