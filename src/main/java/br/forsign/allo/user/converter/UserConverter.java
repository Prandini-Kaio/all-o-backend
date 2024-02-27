package br.forsign.allo.user.converter;

import br.forsign.allo.user.domain.User;
import br.forsign.allo.user.model.UserOutputDTO;

public class UserConverter {

    public static UserOutputDTO toUserDTO(User user){
        return UserOutputDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .documents(DocumentConverter.toDTO(user.getDocument()))
                .contact(ContactConverter.toDTO(user.getContact()))
                .build();
    }
}
