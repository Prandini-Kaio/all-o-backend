package br.forsign.allo.user.service.actions.contact;

import br.forsign.allo.user.domain.Contact;
import br.forsign.allo.user.model.contact.ContactInputDTO;
import br.forsign.allo.user.repository.ContactRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ContactCreator {

    @Resource
    private ContactRepository repository;

    public Contact create(ContactInputDTO input){
        Contact contact = new Contact();
        contact.setPhone(input.getPhone());
        contact.setEmail(input.getEmail());

        return repository.save(contact);
    }
}
