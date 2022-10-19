package com.SGSJ.Saturn.exceptions;

public class InvalidRegisterDataException extends Exception{

    private final String passwordErrorMessage = "Datos inválidos\n\n" +
            "- Verifique que la contraseña cumpla con los parámetros mostrados.\n" +
            "- Verifique que las contraseñas coincidan entre ellas.\n" +
            "- Verifique que los campos no esten vaciós.";
    private final String dialogErrorTitle = "Usuario Inválido";

    public InvalidRegisterDataException() {
       super();
    }

    public String getPasswordErrorMessage() {
        return passwordErrorMessage;
    }

    public String getDialogErrorTitle() {
        return dialogErrorTitle;
    }
}
