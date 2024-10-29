package net.samitkumar.person_management.service;

import lombok.RequiredArgsConstructor;
import net.samitkumar.person_management.DataNotFoundException;
import net.samitkumar.person_management.entity.Address;
import net.samitkumar.person_management.repository.AddressRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {
    final AddressRepository addressRepository;

    public Mono<List<Address>> getPersonAddress(long personId) {
        return Mono.fromCallable(() -> addressRepository.findByPersonId(personId));
    }

    public Mono<Address> createAddress(long personId, Address address) {
        address.setPersonId(personId);
        return Mono.fromCallable(() -> addressRepository.save(address));
    }

    public Mono<Address> getAddressById(long personId, long addressId) {
        return Mono.fromCallable(() -> addressRepository.findByPersonIdAndId(personId, addressId).orElseThrow(() -> new DataNotFoundException("Address not found")));
    }

    public Mono<Address> updateAddress(long personId, long addressId, Address address) {
        return getAddressById(personId, addressId)
                .map(existingAddress -> {
                    existingAddress.setAddressType(address.getAddressType());
                    existingAddress.setCity(address.getCity());
                    existingAddress.setCountry(address.getCountry());
                    existingAddress.setState(address.getState());
                    existingAddress.setStreet(address.getStreet());
                    existingAddress.setZipCode(address.getZipCode());
                    return existingAddress;
                })
                .map(addressRepository::save);
    }


    public Mono<Address> partialUpdateAddress(long personId, long addressId, Mono<Address> addressMono) {
        return getAddressById(personId, addressId)
                .zipWith(addressMono,(dbAddress, reqAddress) -> {
                    if (reqAddress.getAddressType() != null) {
                        dbAddress.setAddressType(reqAddress.getAddressType());
                    }
                    if (reqAddress.getCity() != null) {
                        dbAddress.setCity(reqAddress.getCity());
                    }
                    if (reqAddress.getCountry() != null) {
                        dbAddress.setCountry(reqAddress.getCountry());
                    }
                    if (reqAddress.getState() != null) {
                        dbAddress.setState(reqAddress.getState());
                    }
                    if (reqAddress.getStreet() != null) {
                        dbAddress.setStreet(reqAddress.getStreet());
                    }
                    if (reqAddress.getZipCode() != null) {
                        dbAddress.setZipCode(reqAddress.getZipCode());
                    }
                    return dbAddress;
                })
                .map(addressRepository::save);

    }

    public Mono<Void> deleteAddress(long personId, long addressId) {
        return Mono.fromRunnable(() -> addressRepository.findByPersonIdAndId(personId, addressId).ifPresent(addressRepository::delete));
    }
}
