package br.forsign.allo.user.service.actions;

import br.forsign.allo.contact.domain.Contact;
import br.forsign.allo.contact.service.action.ContactCreator;
import br.forsign.allo.document.domain.Documents;
import br.forsign.allo.document.service.action.DocumentCreator;
import br.forsign.allo.user.domain.User;
import br.forsign.allo.user.model.UserInputDTO;
import br.forsign.allo.user.repository.UserRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserUpdater {

    @Resource
    private UserRepository repository;
    @Resource
    private DocumentCreator documentCreator;
    @Resource
    private ContactCreator contactCreator;


    public User update(UserInputDTO inputDTO){

        User user = new User();
        user = repository.findById(inputDTO.getId()).orElse(null);

        User newUser = new User();

        newUser.setName(inputDTO.getName());
        newUser.getContacts().setEmail(inputDTO.getContact().getEmail());
        newUser.getContacts().setEmail(inputDTO.getContact().getPhone());
        newUser.getDocuments().setCpf_cnpj(inputDTO.getDocuments().getCpfCnpj());

        repository.save(newUser);


//        repository.findById(user.getId()).ifPresent(newUser -> {
//            newUser.setName(inputDTO.getName());
//            newUser.setDocuments(documents);
//            newUser.setContacts(contact);
//            repository.save(newUser);
//        });
    return user;
    }
}
