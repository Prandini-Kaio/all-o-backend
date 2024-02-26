package br.forsign.allo.user.service;

import br.forsign.allo.user.converter.UserConverter;
import br.forsign.allo.user.domain.User;
import br.forsign.allo.user.model.UserDTO;
import br.forsign.allo.user.model.UserInputDTO;
import br.forsign.allo.user.repository.UserRepository;
import br.forsign.allo.user.service.actions.UserCreator;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    @Resource
    private UserCreator creator;

    public List<UserDTO> findAll(){
        List<User> users = repository.findAll();
        return users.stream().map(UserConverter::toUserDTO).toList();
    }

    public UserDTO create(UserInputDTO inputDTO){
        return UserConverter.toUserDTO(creator.create(inputDTO));
    }
}
