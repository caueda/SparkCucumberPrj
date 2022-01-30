package com.example.gft.dto;

import com.example.gft.mapper.UserMapper;
import com.example.gft.model.User;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@JacksonXmlRootElement(localName = "userList")
public class UserDTOList {
    private List<UserDTO> users;

    public UserDTOList(Collection<User> usersCollection) {
        UserMapper userMapper = Mappers.getMapper(UserMapper.class);
        this.users = new ArrayList<>();
        usersCollection.stream().map(userMapper::toDTO).forEach(this.users::add);
    }
}
