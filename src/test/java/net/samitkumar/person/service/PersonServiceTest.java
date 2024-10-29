package net.samitkumar.person.service;

import net.samitkumar.person.entity.Address;
import net.samitkumar.person.entity.ContactInfo;
import net.samitkumar.person.entity.Person;
import net.samitkumar.person.entity.SocialMedia;
import net.samitkumar.person.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class PersonServiceTest {

    @MockBean
    PersonRepository personRepository;

    @BeforeEach
    void setUp() {
        var db = List.of(
                Person.builder()
                        .id(1L)
                        .firstName("John")
                        .lastName("Doe")
                        .profilePhotoUrl("/home.john.png")
                        .birthDate(LocalDate.of(1990, 1, 1))
                        .address(Set.of())
                        .contactInfo(Set.of())
                        .socialMediaAccount(Set.of())
                        .build(),
                Person.builder()
                        .id(2L)
                        .firstName("Jane")
                        .lastName("Doe")
                        .profilePhotoUrl("/home.jane.png")
                        .birthDate(LocalDate.of(1990, 1, 1))
                        .address(Set.of(
                                Address.builder()
                                        .id(1L)
                                        .addressType(Address.AddressType.HOME)
                                        .country("India")
                                        .city("Delhi")
                                        .street("Khau Gali")
                                        .state("UP")
                                        .zipCode("700000")
                                        .build(),
                                Address.builder()
                                        .id(1L)
                                        .addressType(Address.AddressType.WORK)
                                        .country("Europe")
                                        .city("Kobenhagen")
                                        .street("Middle Fart")
                                        .zipCode("1200")
                                        .build()
                        ))
                        .contactInfo(Set.of(
                                ContactInfo.builder()
                                        .id(1L)
                                        .contactInfoType(ContactInfo.ContactInfoType.PERSONAL)
                                        .email("john@unknown.net")
                                        .phoneNumber("31323334")
                                        .build()
                        ))
                        .socialMediaAccount(Set.of(
                                SocialMedia.builder()
                                        .id(1L)
                                        .handle("john_doe")
                                        .platform(SocialMedia.Platform.X)
                                        .build()
                        ))
                        .build()
        );

        when(personRepository.findAll()).thenReturn(db);
        when(personRepository.findById(1L)).thenReturn(java.util.Optional.of(db.get(0)));
        when(personRepository.findById(2L)).thenReturn(java.util.Optional.of(db.get(1)));
    }
}
