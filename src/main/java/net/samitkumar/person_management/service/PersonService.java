package net.samitkumar.person_management.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.samitkumar.person_management.DataNotFoundException;
import net.samitkumar.person_management.entity.Person;
import net.samitkumar.person_management.model.PersonRequest;
import net.samitkumar.person_management.repository.PersonRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

import static net.samitkumar.person_management.UtilityMapper.addressRequestToAddressEntityMapper;
import static net.samitkumar.person_management.UtilityMapper.addressRequestToContactInfoMapper;
import static net.samitkumar.person_management.UtilityMapper.addressRequestToSocialMediaEntityMapper;

@Service
@RequiredArgsConstructor
@Slf4j
public class PersonService {

    final PersonRepository personRepository;

    public Mono<List<Person>> getAllPersons() {
        return Mono.fromCallable(personRepository::findAll);
    }

    public Mono<Person> createPerson(PersonRequest personRequest) {
        log.info("Creating person with request: {}", personRequest);
        return Mono.fromCallable(() -> {
            return Person.builder()
                    .firstName(personRequest.getFirstName())
                    .lastName(personRequest.getLastName())
                    .birthDate(personRequest.getBirthDate())
                    .profilePhotoUrl(personRequest.getProfilePhotoUrl())
                    .address(addressRequestToAddressEntityMapper(personRequest.getAddress()))
                    .contactInfo(addressRequestToContactInfoMapper(personRequest.getContactInfo()))
                    .socialMediaAccount(addressRequestToSocialMediaEntityMapper(personRequest.getSocialMediaAccount()))
                    .build();
        }).map(personRepository::save);
    }

    public Mono<Person> getPerson(long personId) {
        return Mono
                .fromCallable(() -> personRepository.findById(personId)
                        .orElseThrow(() -> new DataNotFoundException("Person not found")));
    }

    public Mono<Person> updatePerson(long personId, PersonRequest personRequest) {
        return getPerson(personId)
                .doOnNext(dbPerson -> log.info("Updating person with request: {}", personRequest))
                .map(dbPerson -> {
                    dbPerson.setFirstName(personRequest.getFirstName());
                    dbPerson.setLastName(personRequest.getLastName());
                    dbPerson.setBirthDate(personRequest.getBirthDate());
                    dbPerson.setProfilePhotoUrl(personRequest.getProfilePhotoUrl());
                    dbPerson.setAddress(addressRequestToAddressEntityMapper(personRequest.getAddress()));
                    dbPerson.setContactInfo(addressRequestToContactInfoMapper(personRequest.getContactInfo()));
                    dbPerson.setSocialMediaAccount(addressRequestToSocialMediaEntityMapper(personRequest.getSocialMediaAccount()));
                    return dbPerson;
                })
                .map(personRepository::save);
    }

    public Mono<Person> partialUpdatePerson(long personId, Mono<PersonRequest> personRequest) {
        return getPerson(personId).zipWith(personRequest, (db, personRequestObject) -> {
            if (personRequestObject.getFirstName() != null) {
                db.setFirstName(personRequestObject.getFirstName());
            }
            if (personRequestObject.getLastName() != null) {
                db.setLastName(personRequestObject.getLastName());
            }
            if (personRequestObject.getBirthDate() != null) {
                db.setBirthDate(personRequestObject.getBirthDate());
            }
            if (personRequestObject.getProfilePhotoUrl() != null) {
                db.setProfilePhotoUrl(personRequestObject.getProfilePhotoUrl());
            }

            return db;
        }).map(personRepository::save);
    }

    public Mono<Void> deletePerson(long personId) {
        return Mono.fromRunnable(() -> personRepository.deleteById(personId));
    }
}
