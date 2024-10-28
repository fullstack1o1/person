package net.samitkumar.person_management.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
public class PersonRequest {
    String firstName;
    String lastName;
    LocalDate birthDate;
    String profilePhotoUrl;
    Set<AddressRequest> address;
    Set<ContactInfoRequest> contactInfo;
    Set<SocialMediaRequest> socialMediaAccount;
}
