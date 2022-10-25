package com.SGSJ.Saturn.rest_controller;

import com.SGSJ.Saturn.domain.User.User;
import com.SGSJ.Saturn.domain.User.UserService;
import com.SGSJ.Saturn.exceptions.InvalidFileTypeException;
import com.SGSJ.Saturn.exceptions.InvalidLogInException;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;

@RestController
@RequestMapping("/user")
public class UserRestController {
    @Autowired
    private UserService userService;
    @Value("${spring.application.pdf-folder}")
    private String pdfPath;
    @Value("${spring.profiles.active}")
    private String env;

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/add", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> addNewUser(@ModelAttribute User user, @RequestParam String mainPhone, @RequestParam String secondaryPhone) {
        ArrayList<String> phoneNumbers = new ArrayList<>();
        phoneNumbers.add(mainPhone);
        phoneNumbers.add(secondaryPhone);
        user.setPhoneNumbers(phoneNumbers);

        MultipartFile document = user.getDocumentPDF();
        String documentType = document.getContentType();
        pdfPath = "/dist/resources/CV/";
        if(!env.equals("prod")) pdfPath = "/libs/dist/resources/CV/";

        try {
            assert documentType != null;
            if(!documentType.equals("application/pdf")) throw new InvalidFileTypeException();

            File jarFile = new File(new ClassPathResource("").getURL().getPath());
            File appFile = jarFile.getParentFile().getParentFile().getParentFile();
            File finalFile = new File(appFile + pdfPath + document.getOriginalFilename());

            String pathFormatted = finalFile.getPath().replace("file:\\", "");
            OutputStream os = new FileOutputStream(pathFormatted);
            os.write(document.getBytes());

            user.setPathToCV(pathFormatted);
        } catch (IOException | InvalidFileTypeException | AssertionError e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userService.add(user), HttpStatus.ACCEPTED);
    }
}
