package br.forsign.allo.user.service;

import br.forsign.allo.user.converter.UserConverter;
import br.forsign.allo.user.domain.User;
import br.forsign.allo.user.model.UserOutputDTO;
import br.forsign.allo.user.model.UserInputDTO;
import br.forsign.allo.user.repository.UserRepository;
import br.forsign.allo.user.service.actions.*;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    @Resource
    private UserCreator creator;

    @Resource
    private UserGetter getter;

    @Resource
    private UserDeleter deleter;

    @Resource
    private UserUpdater updater;

    @Resource
    private UserValidator validator;


    public List<UserOutputDTO> findAll(){
        List<User> users = repository.findAll();
        return users.stream().map(UserConverter::toUserDTO).toList();
    }

    public UserOutputDTO create(UserInputDTO inputDTO){
        validator.validarUsuarioCreate(inputDTO);
        return UserConverter.toUserDTO(creator.create(inputDTO));
    }

    public UserOutputDTO getById(Long id){
        return UserConverter.toUserDTO(getter.getById(id));
    }

    public void delById(Long id){
       deleter.delById(id);
    }

    public UserOutputDTO updateById(UserInputDTO inputDTO){
        return UserConverter.toUserDTO(updater.update(inputDTO));
    }
}