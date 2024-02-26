package br.forsign.allo.user.service.actions;

import br.forsign.allo.user.domain.User;
import br.forsign.allo.user.repository.UserRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class UserDeleter {

    @Resource
    UserRepository repository;

    public void delById(Long id){
        repository.deleteById(id);
    }
}