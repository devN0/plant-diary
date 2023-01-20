package com.plantdiary.enterprise.exceptions;

public class SpecimenNotFoundException extends IllegalArgumentException{
    public SpecimenNotFoundException(long id) {
        super("No specimen found with id : "+ id);
    }
}
