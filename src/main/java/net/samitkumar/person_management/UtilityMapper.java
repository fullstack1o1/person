package net.samitkumar.person_management;

import lombok.experimental.UtilityClass;
import net.samitkumar.person_management.entity.Address;
import net.samitkumar.person_management.entity.ContactInfo;
import net.samitkumar.person_management.entity.SocialMedia;
import net.samitkumar.person_management.model.AddressRequest;
import net.samitkumar.person_management.model.ContactInfoRequest;
import net.samitkumar.person_management.model.SocialMediaRequest;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@UtilityClass
public class UtilityMapper {

    public static Set<SocialMedia> addressRequestToSocialMediaEntityMapper(Set<SocialMediaRequest> socialMediaRequests) {
        if (Objects.isNull(socialMediaRequests)) {
            return Set.of();
        }

        return socialMediaRequests.stream().map(socialMediaRequest -> {
            return SocialMedia.builder()
                    .handle(socialMediaRequest.getHandle())
                    .platform(socialMediaRequest.getPlatform())
                    .build();
        }).collect(Collectors.toSet());
    }

    public static Set<ContactInfo> addressRequestToContactInfoMapper(Set<ContactInfoRequest> contactInfoRequests) {
        if (Objects.isNull(contactInfoRequests)) {
            return Set.of();
        }
        return contactInfoRequests.stream().map(contactInfoRequest -> {
            return ContactInfo.builder()
                    .contactInfoType(contactInfoRequest.getContactInfoType())
                    .phoneNumber(contactInfoRequest.getPhoneNumber())
                    .email(contactInfoRequest.getEmail())
                    .build();
        }).collect(Collectors.toSet());
    }

    public static Set<Address> addressRequestToAddressEntityMapper(Set<AddressRequest> addressRequests) {
        if (Objects.isNull(addressRequests)) {
            return Set.of();
        }
        return addressRequests.stream().map(addressRequest -> {
            return Address.builder()
                    .addressType(addressRequest.getAddressType())
                    .country(addressRequest.getCountry())
                    .state(addressRequest.getState())
                    .city(addressRequest.getCity())
                    .zipCode(addressRequest.getZipCode())
                    .street(addressRequest.getStreet())
                    .build();
        }).collect(Collectors.toSet());
    }
}
