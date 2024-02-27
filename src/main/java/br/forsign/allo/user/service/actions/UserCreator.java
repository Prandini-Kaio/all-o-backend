package br.forsign.allo.user.service.actions;

import br.forsign.allo.user.domain.Contact;
import br.forsign.allo.user.service.actions.contact.ContactCreator;
import br.forsign.allo.user.domain.Document;
import br.forsign.allo.user.service.actions.document.DocumentCreator;
import br.forsign.allo.user.domain.User;
import br.forsign.allo.user.model.UserInputDTO;
import br.forsign.allo.user.repository.UserRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class UserCreator {

    @Resource
    private UserRepository repository;
    @Resource
    private DocumentCreator documentCreator;
    @Resource
    private ContactCreator contactCreator;

    public User create(UserInputDTO inputDTO){
        // TODO --> DEVEMOS ADICIONAR UM VALIDATOR, PARA VALIDAR A CRIACAO DO USUARIO ESSE TIPO DE ACAO SE DEVE A SEPARACAO DE FUNCIONALIDADES
        User user = new User();

        Document documents = documentCreator.create(inputDTO.getDocuments());
        Contact contact = contactCreator.create(inputDTO.getContact());

        user.setName(inputDTO.getName());
        user.setDocuments(documents);
        user.setContacts(contact);

        User savedUser = repository.save(user);

        return user;
    }
}
