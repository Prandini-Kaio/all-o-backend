package br.forsign.allo.user.service;

import br.forsign.allo.user.domain.User;
import br.forsign.allo.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    public void createUser(String name){
        User user = new User();
        repository.save(user);
    }

}
