package br.forsign.allo.user.converter;

import br.forsign.allo.user.domain.Contact;
import br.forsign.allo.user.model.contact.ContactDTO;

public class ContactConverter {
    public static ContactDTO toDTO(Contact contact){
        return new ContactDTO(contact.getId(), contact.getEmail(), contact.getPhone());
    }
}
