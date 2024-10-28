package net.samitkumar.person_management.model;

import lombok.Builder;
import lombok.Data;
import net.samitkumar.person_management.entity.SocialMedia;

@Data
@Builder
public class SocialMediaRequest {
    SocialMedia.Platform platform;
    String handle;
}
