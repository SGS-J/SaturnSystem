package com.SGSJ.Saturn.exceptions;

public class InvalidLogInException extends Exception{
    private final String dialogErrorTitle = "Datos inválidos!";
    private final String invalidDataErrorMessage = "Datos inválidos. Favor verifique los datos ingresados y si el usuario existe.";
    public InvalidLogInException() {
        super();
    }

    public String getDialogErrorTitle() {
        return dialogErrorTitle;
    }

    public String getInvalidDataErrorMessage() {
        return invalidDataErrorMessage;
    }
}
