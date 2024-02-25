package br.forsign.allo.contact.service.action;

import br.forsign.allo.contact.domain.Contact;
import br.forsign.allo.contact.model.ContactDTO;
import br.forsign.allo.contact.repository.ContactRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class ContactCreator {

    @Resource
    private ContactRepository repository;

    public Contact create(ContactDTO contactDTO){
        Contact contact = new Contact();
        contact.setPhone(contactDTO.getPhone());
        contact.setEmail(contactDTO.getEmail());

        return repository.save(contact);
    }
}
