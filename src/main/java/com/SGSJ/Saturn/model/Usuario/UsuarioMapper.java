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
            @Mapping(source = "UsuarioID", target = "userId"),
            @Mapping(source = "UsuarioNombre", target = "name"),
            @Mapping(source = "HojaVidaPath", target = "pathToCV"),
            @Mapping(source = "Telefonos", target = "phoneNumbers"),
            @Mapping(source = "Estado", target = "state"),
            @Mapping(target = "UsuarioUUID", ignore = true),
            @Mapping(target = "UsuarioVacante", ignore = true),
    })
    User toUser(Usuario usuario);
    List<User> toUsers(List<Usuario> usuarioList);

    @InheritInverseConfiguration
    Usuario toUsuario(User user);
    List<Usuario> toUsuarios(List<Usuario> usuarioList);
}
