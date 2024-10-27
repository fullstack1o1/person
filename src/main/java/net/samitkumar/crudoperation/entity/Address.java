package net.samitkumar.crudoperation.entity;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
public class Address {
    @Id
    Long id;
    AddressType type;
    Long personId; //Reference Key
    String street;
    String city;
    String state;
    String zipCode;
    String country;

    public enum AddressType {HOME, WORK}
}
