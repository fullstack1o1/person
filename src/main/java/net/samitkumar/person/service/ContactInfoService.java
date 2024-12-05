package net.samitkumar.person.service;

import lombok.RequiredArgsConstructor;
import net.samitkumar.person.DataNotFoundException;
import net.samitkumar.person.entity.ContactInfo;
import net.samitkumar.person.repository.ContactInfoRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactInfoService {
    final ContactInfoRepository contactInfoRepository;

    public Mono<List<ContactInfo>> getPersonContactInfo(long personId) {
        return Mono.fromCallable(() -> contactInfoRepository.findByPersonId(personId));
    }

    public Mono<ContactInfo> createContactInfo(long personId, ContactInfo contactInfo) {
        return Mono.fromCallable(() -> {
            contactInfo.setPersonId(personId);
            return contactInfoRepository.save(contactInfo);
        });
    }

    public Mono<ContactInfo> getContactInfo(long personId, long contactInfoId) {
        return Mono.fromCallable(() -> contactInfoRepository.findByPersonIdAndId(personId, contactInfoId)
                .orElseThrow(() -> new DataNotFoundException("ContactInfo not found")));
    }

    public Mono<ContactInfo> updateContactInfo(long personId, long contactInfoId, ContactInfo contactInfo) {
        return getContactInfo(personId, contactInfoId)
                .map(existingContactInfo -> {
                    existingContactInfo.setContactInfoType(contactInfo.getContactInfoType());
                    existingContactInfo.setEmail(contactInfo.getEmail());
                    existingContactInfo.setPhoneNumber(contactInfo.getPhoneNumber());
                    return existingContactInfo;
                })
                .map(contactInfoRepository::save);
    }

    public Mono<ContactInfo> partialUpdateContactInfo(long personId, long contactInfoId, Mono<ContactInfo> contactInfoMono) {
        return getContactInfo(personId, contactInfoId)
                .zipWith(contactInfoMono, (existingContactInfo, updatedContactInfo)-> {
                    if (updatedContactInfo.getContactInfoType() != null) {
                        existingContactInfo.setContactInfoType(updatedContactInfo.getContactInfoType());
                    }
                    if (updatedContactInfo.getEmail() != null) {
                        existingContactInfo.setEmail(updatedContactInfo.getEmail());
                    }
                    if (updatedContactInfo.getPhoneNumber() != null) {
                        existingContactInfo.setPhoneNumber(updatedContactInfo.getPhoneNumber());
                    }
                    return existingContactInfo;
                })
                .map(contactInfoRepository::save);
    }

    public Mono<Void> deleteContactInfo(long personId, long contactInfoId) {
        return Mono.fromRunnable(() -> contactInfoRepository.findByPersonIdAndId(personId, contactInfoId).ifPresent(contactInfoRepository::delete));
    }

    public Mono<List<ContactInfo>> searchContactInfoByEmail(String s) {
        return Mono.fromCallable(() -> contactInfoRepository.findContactInfoByEmailContainingIgnoreCase(s));
    }
}
