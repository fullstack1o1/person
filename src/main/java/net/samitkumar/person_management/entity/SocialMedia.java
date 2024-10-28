package net.samitkumar.person_management.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
public class SocialMedia {
    @Id
    Long id;
    Long personId; // Foreign Key
    Platform platform;
    String handle;

    public enum Platform { FACEBOOK, X, INSTAGRAM }
}
