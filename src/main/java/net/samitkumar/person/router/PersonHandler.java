package net.samitkumar.person.router;

import lombok.RequiredArgsConstructor;
import net.samitkumar.person.model.PersonRequest;
import net.samitkumar.person.service.PersonService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class PersonHandler {
    final PersonService personService;

    public Mono<ServerResponse> getAllPersons(ServerRequest request) {
        return personService
                .getAllPersons()
                .flatMap(ServerResponse.ok()::bodyValue);
    }

    public Mono<ServerResponse> createPerson(ServerRequest request) {
        return request
                .bodyToMono(PersonRequest.class)
                .flatMap(personService::createPerson)
                .flatMap(ServerResponse.ok()::bodyValue);
    }

    public Mono<ServerResponse> getPerson(ServerRequest request) {
        var personId = Long.parseLong(request.pathVariable("personId"));
        return personService
                .getPerson(personId)
                .flatMap(ServerResponse.ok()::bodyValue);
    }

    public Mono<ServerResponse> updatePerson(ServerRequest request) {
        var personId = Long.parseLong(request.pathVariable("personId"));
        return request
                .bodyToMono(PersonRequest.class)
                .flatMap(personRequest -> personService.updatePerson(personId, personRequest))
                .flatMap(ServerResponse.ok()::bodyValue);
    }

    public Mono<ServerResponse> partialUpdatePerson(ServerRequest request) {
        var personId = Long.parseLong(request.pathVariable("personId"));
        return personService.partialUpdatePerson(personId, request.bodyToMono(PersonRequest.class))
                .flatMap(ServerResponse.ok()::bodyValue);
    }

    public Mono<ServerResponse> deletePerson(ServerRequest request) {
        var personId = Long.parseLong(request.pathVariable("personId"));
        return personService
                .deletePerson(personId)
                .then(ServerResponse.ok().build());
    }
}
