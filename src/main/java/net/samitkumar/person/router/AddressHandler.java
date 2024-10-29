package net.samitkumar.person.router;

import lombok.RequiredArgsConstructor;
import net.samitkumar.person.entity.Address;
import net.samitkumar.person.service.AddressService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AddressHandler {
    final AddressService addressService;

    public Mono<ServerResponse> getPersonAddress(ServerRequest serverRequest) {
        var personId = Long.parseLong(serverRequest.pathVariable("personId"));
        return addressService.getPersonAddress(personId)
                .flatMap(ServerResponse.ok()::bodyValue);
    }

    public Mono<ServerResponse> createAddress(ServerRequest serverRequest) {
        var personId = Long.parseLong(serverRequest.pathVariable("personId"));
        return serverRequest
                .bodyToMono(Address.class)
                .flatMap(address -> addressService.createAddress(personId, address))
                .flatMap(ServerResponse.ok()::bodyValue);
    }

    public Mono<ServerResponse> getAddressById(ServerRequest request) {
        var personId = Long.parseLong(request.pathVariable("personId"));
        var addressId = Long.parseLong(request.pathVariable("addressId"));
        return addressService.getAddressById(personId, addressId)
                .flatMap(ServerResponse.ok()::bodyValue);
    }

    public Mono<ServerResponse> updateAddress(ServerRequest request) {
        var personId = Long.parseLong(request.pathVariable("personId"));
        var addressId = Long.parseLong(request.pathVariable("addressId"));
        return request
                .bodyToMono(Address.class)
                .flatMap(address -> addressService.updateAddress(personId, addressId, address))
                .flatMap(ServerResponse.ok()::bodyValue);
    }

    public Mono<ServerResponse> partialUpdateAddress(ServerRequest request) {
        var personId = Long.parseLong(request.pathVariable("personId"));
        var addressId = Long.parseLong(request.pathVariable("addressId"));
        return addressService.partialUpdateAddress(personId, addressId, request.bodyToMono(Address.class))
                .flatMap(ServerResponse.ok()::bodyValue);
    }

    public Mono<ServerResponse> deleteAddress(ServerRequest request) {
        var personId = Long.parseLong(request.pathVariable("personId"));
        var addressId = Long.parseLong(request.pathVariable("addressId"));
        return addressService.deleteAddress(personId, addressId)
                .then(ServerResponse.ok().build());
    }
}
