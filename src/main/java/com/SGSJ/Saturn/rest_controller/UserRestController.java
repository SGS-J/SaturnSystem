package com.SGSJ.Saturn.rest_controller;

import com.SGSJ.Saturn.domain.User.User;
import com.SGSJ.Saturn.domain.User.UserService;
import com.SGSJ.Saturn.exceptions.InvalidFileTypeException;
import com.SGSJ.Saturn.exceptions.InvalidLogInException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequestMapping("/user")
public class UserRestController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/add", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> addNewUser(@ModelAttribute User user, @RequestParam String mainPhone, @RequestParam String secondaryPhone) {
        ArrayList<String> phoneNumbers = new ArrayList<>();
        phoneNumbers.add(mainPhone);
        phoneNumbers.add(secondaryPhone);
        user.setPhoneNumbers(phoneNumbers);

        MultipartFile document = user.getDocumentPDF();
        String documentType = document.getContentType();

        try {
            assert documentType != null;
            if(!documentType.equals("application/pdf")) throw new InvalidFileTypeException();

            String path = new ClassPathResource("/CV").getURI().getPath()
                    + "/" + document.getOriginalFilename();
            File file = new File(path);
            document.transferTo(file);
            user.setPathToCV(path);
        } catch (IOException | InvalidFileTypeException | AssertionError e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userService.add(user), HttpStatus.ACCEPTED);
    }
}
