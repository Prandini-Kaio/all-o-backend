package br.forsign.allo.contact.converter;

import br.forsign.allo.contact.domain.Contact;
import br.forsign.allo.contact.model.ContactDTO;

public class ContactConverter {
    public static ContactDTO toDTO(Contact contact){
        return new ContactDTO(contact.getId(), contact.getEmail(), contact.getPhone());
    }
}
