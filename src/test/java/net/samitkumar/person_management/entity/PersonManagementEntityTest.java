package net.samitkumar.person_management.entity;

import net.samitkumar.person_management.TestcontainersConfiguration;
import net.samitkumar.person_management.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertAll;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
public class PersonManagementEntityTest {

    @Autowired
    PersonRepository personRepository;

    @Test
    void entityTest() {
        assertAll(
                //CREATE
                () -> {
                    personRepository.save(
                            Person.builder()
                                    .firstName("John")
                                    .lastName("Doe")
                                    .birthDate(LocalDate.now())
                                    .profilePhotoUrl("/home/photo/p1.png")
                                    .address(Set.of(
                                            Address.builder()
                                                    .addressType(Address.AddressType.HOME)
                                                    .country("India")
                                                    .city("Delhi")
                                                    .street("Khau Gali")
                                                    .state("UP")
                                                    .zipCode("700000")
                                                    .build(),
                                            Address.builder()
                                                    .addressType(Address.AddressType.WORK)
                                                    .country("Europe")
                                                    .city("Kobenhagen")
                                                    .street("Middle Fart")
                                                    .zipCode("1200")
                                                    .build()
                                    ))
                                    .contactInfo(Set.of(
                                            ContactInfo.builder()
                                                    .contactInfoType(ContactInfo.ContactInfoType.PERSONAL)
                                                    .email("unknown@unknown.net")
                                                    .phoneNumber("31323334")
                                                    .build(),
                                            ContactInfo.builder()
                                                    .contactInfoType(ContactInfo.ContactInfoType.WORK)
                                                    .email("unknown@abc.net")
                                                    .phoneNumber("31323334")
                                                    .build()
                                    ))
                                    .build()
                    );
                },
                //LIST
                () -> {
                    personRepository
                            .findAll()
                            .forEach(System.out::println);

                    //Person(id=1, firstName=John, lastName=Doe, birthDate=2024-10-27, profilePhotoUrl=/home/photo/p1.png, address=[Address(id=1, type=HOME, personId=1, street=Khau Gali, city=Delhi, state=UP, zipCode=700000, country=India)], createdAt=2024-10-27T22:49:38.025661, updatedAt=2024-10-27T22:49:38.025661)
                }
        );
    }


}
