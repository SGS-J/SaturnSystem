package com.SGSJ.Saturn.controller.Register.util;

import com.SGSJ.Saturn.domain.Employee.Employee;
import com.SGSJ.Saturn.exceptions.InvalidRegisterDataException;

import java.util.regex.Pattern;

public class RegisterVerificator {

    public static void verifyEmptyFields(Employee employee) throws InvalidRegisterDataException{
        boolean emptyFields = false;
        emptyFields = employee.getName().equals("");
        emptyFields = employee.getPassword().equals("");
        emptyFields = employee.getPost().equals("");
        if(emptyFields) throw new InvalidRegisterDataException();
    }

    public static void verifyPassword(String passwordMain, String passwordRepeat) throws InvalidRegisterDataException {
        boolean passwordPatternMatchs = matchPattern(passwordMain);
        boolean passwordEquals = passwordMain.equals(passwordRepeat);

        if(!passwordPatternMatchs || !passwordEquals) {
            throw new InvalidRegisterDataException();
        }
    }

    private static boolean matchPattern(String password) {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{8,}$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        return pattern.matcher(password).find();
    }
}
