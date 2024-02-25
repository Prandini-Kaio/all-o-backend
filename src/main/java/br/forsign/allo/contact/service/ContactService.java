package br.forsign.allo.contact.service;

import br.forsign.allo.contact.converter.ContactConverter;
import br.forsign.allo.contact.domain.Contact;
import br.forsign.allo.contact.model.ContactDTO;
import br.forsign.allo.contact.service.action.ContactCreator;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    @Resource
    private ContactCreator creator;

    public ContactDTO create(ContactDTO contact){
        return ContactConverter.toDTO(creator.create(contact));
    }
}
