package com.SGSJ.Saturn.domain.User;

import com.SGSJ.Saturn.model.Usuario.Usuario;
import com.SGSJ.Saturn.model.Usuario.UsuarioMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDTO {
    @Autowired
    private UsuarioMapper mapper;

    public User toUser(Usuario usuario) {
        String telefonos = usuario.getTelefonos();
        JSONParser jsonParser = new JSONParser();
        User user = mapper.toUser(usuario);

        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(telefonos);
            JSONArray jsonArray = (JSONArray) jsonObject.get("telefonos");
            Iterator<String> iterator = jsonArray.iterator();

            ArrayList<String> phoneNumbers = new ArrayList<>();

            while(iterator.hasNext()) {
                phoneNumbers.add(iterator.next());
            }

            user.setPhoneNumbers(phoneNumbers);
        } catch (ParseException e) {
            e.printStackTrace();
            ArrayList<String> phoneNumbers = new ArrayList<>();
            user.setPhoneNumbers(phoneNumbers);
        }

        return user;
    }

  public List<User> toUsers(List<Usuario> usuarios) {
        return usuarios.stream().map(this::toUser).collect(Collectors.toList());
    }

    public Usuario toUsuario(User user) {
        StringBuilder stringBuilder = new StringBuilder();

        // Create json object string
        stringBuilder.append("[");
        if(!user.getPhoneNumbers().isEmpty()) {
            user.getPhoneNumbers()
                    .forEach(num -> stringBuilder
                            .append("\"").append(num)
                            .append("\"").append(","));
            stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(","));
        }
        stringBuilder.append("]");
        String arrayString = stringBuilder.toString();
        String jsonString = "{ \"telefonos\": " + arrayString + " }";
        // End of creating json object string

        System.out.println(jsonString);
        Usuario usuario = mapper.toUsuario(user);
        usuario.setTelefonos(jsonString);
        return usuario;
    }

    public List<Usuario> toUsuarios(List<User> users) {
        return users.stream().map(this::toUsuario).collect(Collectors.toList());
    }
}
