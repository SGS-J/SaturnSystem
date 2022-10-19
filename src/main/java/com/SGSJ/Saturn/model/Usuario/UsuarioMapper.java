package com.SGSJ.Saturn.model.Usuario;

import com.SGSJ.Saturn.domain.User.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    @Mappings({
            @Mapping(target = "usuarioID", source = "userId"),
            @Mapping(target = "usuarioNombre", source = "name"),
            @Mapping(target = "usuarioCedula", source = "identification"),
            @Mapping(target = "hojaVidaPath", source = "pathToCV"),
            @Mapping(target = "telefonos", source = "phoneNumbers"),
            @Mapping(target = "correo", source = "email"),
            @Mapping(target = "estado", source = "state"),
            @Mapping(target = "usuarioUUID", ignore = true),
            @Mapping(target = "usuarioVacante", ignore = true),
    })
    Usuario toUsuario(User user);
    List<Usuario> toUsuarios(List<User> usuarioList);

    @InheritInverseConfiguration
    User toUser(Usuario usuario);
    List<User> toUsers(List<Usuario> usuarioList);
}
