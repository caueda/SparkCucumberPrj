package com.example.gft.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;

import java.util.Date;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName = "user")
public class UserDTO {
    private Long id;
    private String name;
    private String surname;
    private Date birthDate;
}
