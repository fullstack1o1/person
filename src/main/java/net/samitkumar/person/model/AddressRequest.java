package net.samitkumar.person.model;

import lombok.Builder;
import lombok.Data;
import net.samitkumar.person.entity.Address;

@Data
@Builder
public class AddressRequest {
    Address.AddressType addressType;
    String street;
    String city;
    String state;
    String zipCode;
    String country;
}
