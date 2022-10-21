package com.SGSJ.Saturn.rest_controller;

import com.SGSJ.Saturn.domain.User.User;
import com.SGSJ.Saturn.domain.User.UserService;
import com.SGSJ.Saturn.exceptions.InvalidFileTypeException;
import com.SGSJ.Saturn.exceptions.InvalidLogInException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

@RestController
@RequestMapping("/user")
public class UserRestController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/add", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public User addNewUser(@ModelAttribute User user, @RequestParam String mainPhone, @RequestParam String secondaryPhone) {
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
        }
        return userService.add(user);
    }
}
