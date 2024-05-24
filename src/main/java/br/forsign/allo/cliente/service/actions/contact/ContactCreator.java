package br.forsign.allo.cliente.service.actions.contact;

import br.forsign.allo.cliente.domain.Contact;
import br.forsign.allo.cliente.model.contact.ContactInputDTO;
import br.forsign.allo.cliente.repository.ContactRepository;
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
