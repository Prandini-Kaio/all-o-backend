package br.forsign.allo.user.service.actions;

import br.forsign.allo.user.domain.User;
import br.forsign.allo.user.exceptions.DocumentException;
import br.forsign.allo.user.exceptions.DocumentExceptionMessages;
import br.forsign.allo.user.model.UserInputDTO;
import br.forsign.allo.user.repository.UserRepository;
import br.forsign.allo.user.service.actions.document.DocumentValidator;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {

    @Resource
    private DocumentValidator documentValidator;

    @Resource
    private UserRepository repository;

    public void validarUsuarioCreate(UserInputDTO inputDTO){
        validarUsuarioExistente(inputDTO);
    }

    private void validarUsuarioExistente(UserInputDTO inputDTO){
        User user = repository.findByDocument_CpfCnpj(inputDTO.getDocument().getCpfCnpj()).orElse(null);

        if(user != null)
            throw new DocumentException(DocumentExceptionMessages.userJaCadastrado(inputDTO.getDocument().getCpfCnpj()));
    }
}
