package com.SGSJ.Saturn.domain.User;

import com.SGSJ.Saturn.model.Usuario.Usuario;
import com.SGSJ.Saturn.model.Usuario.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDTO {
    @Autowired
    private UsuarioMapper mapper;

    public User toUser(Usuario usuario) {
        return mapper.toUser(usuario);
    }

    public List<User> toUsers(List<Usuario> usuarios) {
        return mapper.toUsers(usuarios);
    }

    public Usuario toUsuario(User user) {
        return mapper.toUsuario(user);
    }

    public List<Usuario> toUsuarios(List<User> users) {
        return mapper.toUsuarios(users);
    }
}
