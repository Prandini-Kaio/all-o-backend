package br.forsign.allo.user.service.actions.contact;

import br.forsign.allo.user.converter.ContactConverter;
import br.forsign.allo.user.model.contact.ContactDTO;
import br.forsign.allo.user.model.contact.ContactInputDTO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    @Resource
    private ContactCreator creator;

    public ContactDTO create(ContactInputDTO inputDTO){
        return ContactConverter.toDTO(creator.create(inputDTO));
    }
}
