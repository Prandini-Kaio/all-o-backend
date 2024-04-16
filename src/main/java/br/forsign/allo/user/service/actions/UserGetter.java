package br.forsign.allo.user.service.actions;

import br.forsign.allo.user.domain.User;
import br.forsign.allo.user.repository.UserRepository;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Component

public class UserGetter {

    @Resource
    private UserRepository repository;

    public User getById(Long id){
        return repository.findById(id).orElse(null);
    }
}