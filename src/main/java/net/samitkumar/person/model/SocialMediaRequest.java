package net.samitkumar.person.model;

import lombok.Builder;
import lombok.Data;
import net.samitkumar.person.entity.SocialMedia;

@Data
@Builder
public class SocialMediaRequest {
    SocialMedia.Platform platform;
    String handle;
}
