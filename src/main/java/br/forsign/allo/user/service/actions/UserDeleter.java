package br.forsign.allo.user.service.actions;

import br.forsign.allo.user.repository.UserRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class UserDeleter {
    //Authenticator
    @Resource
    UserRepository repository;

    public void delById(Long id){
        repository.deleteById(id);
    }
}