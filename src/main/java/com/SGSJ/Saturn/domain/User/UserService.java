package com.SGSJ.Saturn.domain.User;

import com.SGSJ.Saturn.model.Usuario.Usuario;
import com.SGSJ.Saturn.model.Usuario.UsuarioCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserRepository{
    @Autowired
    private UserDTO userDTO;
    @Autowired
    private UsuarioCrud usuarioCrud;

    @Override
    public List<User> getAll() {
        return userDTO.toUsers((List<Usuario>) usuarioCrud.findAll());
    }

    @Override
    public User getById(Long ID) {
        return userDTO.toUser(usuarioCrud.findById(ID).get());
    }

    @Override
    public User add(User newUser) {
        return userDTO.toUser(usuarioCrud.save(userDTO.toUsuario(newUser)));
    }

    @Override
    public User updateById(User newUser, Long ID) {
        User oldUser = this.getById(ID);

        oldUser.setName((newUser.getName() == null ? oldUser : newUser).getName());
        oldUser.setEmail((newUser.getEmail() == null ? oldUser : newUser).getEmail());
        oldUser.setPathToCV((newUser.getPathToCV() == null ? oldUser : newUser).getPathToCV());
        oldUser.setPhoneNumbers((newUser.getPhoneNumbers() == null ? oldUser : newUser).getPhoneNumbers());

        return userDTO.toUser(usuarioCrud.save(userDTO.toUsuario(oldUser)));
    }

    @Override
    public void deleteById(Long ID) {
        usuarioCrud.deleteById(ID);
    }

    @Override
    public void updateState(UserState state, Long userId) {
        usuarioCrud.updateUsuarioState(userId, state.toString());
    }

    @Override
    public void setVacancy(Long userId, Long vacancyId) {
        usuarioCrud.setUsuarioVacante(userId, vacancyId);
    }
}
