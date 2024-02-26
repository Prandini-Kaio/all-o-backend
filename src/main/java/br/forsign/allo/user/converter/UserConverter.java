package br.forsign.allo.user.converter;

import br.forsign.allo.contact.converter.ContactConverter;
import br.forsign.allo.document.converter.DocumentConverter;
import br.forsign.allo.user.domain.User;
import br.forsign.allo.user.model.UserDTO;

public class UserConverter {

    public static UserDTO toUserDTO(User user){
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .documents(DocumentConverter.toDTO(user.getDocuments()))
                .contact(ContactConverter.toDTO(user.getContacts()))
                .build();
    }
}
