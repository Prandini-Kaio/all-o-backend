package br.forsign.allo.user.service.actions;

import br.forsign.allo.user.domain.User;
import br.forsign.allo.user.model.UserInputDTO;
import br.forsign.allo.user.repository.UserRepository;
import br.forsign.allo.user.service.actions.contact.ContactCreator;
import br.forsign.allo.user.service.actions.document.DocumentCreator;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

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




        user.setName(inputDTO.getName());
        user.getContact().setEmail(inputDTO.getContact().getEmail());
        user.getContact().setPhone(inputDTO.getContact().getPhone());
        user.getDocument().setCpfCnpj(inputDTO.getDocuments().getCpfCnpj());

        repository.save(user);


    return user;
    }
}
