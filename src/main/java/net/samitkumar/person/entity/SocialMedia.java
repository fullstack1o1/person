package net.samitkumar.person.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
public class SocialMedia {
    @Id
    Long id;
    Long personId; // Foreign Key
    Platform platform;
    String handle;

    public enum Platform { FACEBOOK, X, INSTAGRAM, LINKEDIN, OTHER }
}
