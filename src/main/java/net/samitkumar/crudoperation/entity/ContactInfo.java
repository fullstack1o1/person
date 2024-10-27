package net.samitkumar.crudoperation.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@Table("ContactInfo")
public class ContactInfo {
    @Id
    Long id;
    Long personId; // Foreign Key
    String email;
    String phoneNumber;
}
