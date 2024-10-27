package net.samitkumar.crudoperation.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@Table("SocialMedia")
public class SocialMedia {
    @Id
    Long id;
    Long personId; // Foreign Key
    Platform platform;
    String handle;

    enum Platform { FACEBOOK, X, INSTAGRAM }
}
