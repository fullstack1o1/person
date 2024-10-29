package net.samitkumar.person.router;

import lombok.RequiredArgsConstructor;
import net.samitkumar.person.entity.ContactInfo;
import net.samitkumar.person.service.ContactInfoService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ContactInfoHandler {
    final ContactInfoService contactInfoService;

    public Mono<ServerResponse> getPersonContactInfo(ServerRequest request) {
        var personId = Long.parseLong(request.pathVariable("personId"));
        return contactInfoService.getPersonContactInfo(personId)
                .flatMap(ServerResponse.ok()::bodyValue);
    }

    public Mono<ServerResponse> createContactInfo(ServerRequest request) {
        var personId = Long.parseLong(request.pathVariable("personId"));
        return request
                .bodyToMono(ContactInfo.class)
                .flatMap(contactInfo -> contactInfoService.createContactInfo(personId, contactInfo))
                .flatMap(ServerResponse.ok()::bodyValue);
    }

    public Mono<ServerResponse> getContactInfo(ServerRequest request) {
        var personId = Long.parseLong(request.pathVariable("personId"));
        var contactInfoId = Long.parseLong(request.pathVariable("contactInfoId"));
        return contactInfoService.getContactInfo(personId, contactInfoId)
                .flatMap(ServerResponse.ok()::bodyValue);
    }

    public Mono<ServerResponse> updateContactInfo(ServerRequest request) {
        var personId = Long.parseLong(request.pathVariable("personId"));
        var contactInfoId = Long.parseLong(request.pathVariable("contactInfoId"));
        return request
                .bodyToMono(ContactInfo.class)
                .flatMap(contactInfo -> contactInfoService.updateContactInfo(personId, contactInfoId, contactInfo))
                .flatMap(ServerResponse.ok()::bodyValue);
    }

    public Mono<ServerResponse> partialUpdateContactInfo(ServerRequest request) {
        var personId = Long.parseLong(request.pathVariable("personId"));
        var contactInfoId = Long.parseLong(request.pathVariable("contactInfoId"));
        return contactInfoService.partialUpdateContactInfo(personId, contactInfoId, request.bodyToMono(ContactInfo.class))
                .flatMap(ServerResponse.ok()::bodyValue);
    }

    public Mono<ServerResponse> deleteContactInfo(ServerRequest request) {
        var personId = Long.parseLong(request.pathVariable("personId"));
        var contactInfoId = Long.parseLong(request.pathVariable("contactInfoId"));
        return contactInfoService.deleteContactInfo(personId, contactInfoId)
                .then(ServerResponse.ok().build());
    }
}
