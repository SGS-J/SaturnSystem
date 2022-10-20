package com.SGSJ.Saturn.exceptions;

public class InvalidFileTypeException extends Exception{
    private final String errorMsg = "El archivo debe ser un pdf.";

    public InvalidFileTypeException() {
        super();
    }
}
