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
            @Mapping(target = "hojaVidaPath", source = "pathToCV"),
            @Mapping(target = "correo", source = "email"),
            @Mapping(target = "estado", source = "state"),
            @Mapping(target = "telefonos", ignore = true),
            @Mapping(target = "usuarioUUID", ignore = true),
            @Mapping(target = "usuarioVacante", ignore = true),
    })
    Usuario toUsuario(User user);

    @InheritInverseConfiguration
    @Mapping(target = "documentPDF", ignore = true)
    @Mapping(target = "phoneNumbers", ignore = true)
    User toUser(Usuario usuario);
}
