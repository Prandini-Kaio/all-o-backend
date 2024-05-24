package br.forsign.allo.cliente.converter;

import br.forsign.allo.cliente.domain.Contact;
import br.forsign.allo.cliente.model.contact.ContactDTO;

public class ContactConverter {
    public static ContactDTO toDTO(Contact contact){
        return new ContactDTO(contact.getId(), contact.getEmail(), contact.getPhone());
    }
}
