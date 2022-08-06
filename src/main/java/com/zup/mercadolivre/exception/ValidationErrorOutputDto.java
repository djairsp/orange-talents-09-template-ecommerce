package com.zup.mercadolivre.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorOutputDto {

    private List<String> globalErrorsMessages = new ArrayList<>();
    private List<FieldErrorOutputDto> fieldErrors = new ArrayList<>();

    public void addError(String message){
        globalErrorsMessages.add(message);
    }

    public void addFieldError(String field, String message){
        FieldErrorOutputDto fieldErroOutputDto = new FieldErrorOutputDto(field, message);
        fieldErrors.add(fieldErroOutputDto);
    }

    public List<String> getGlobalErrorsMessages(){
        return globalErrorsMessages;
    }

    public List<FieldErrorOutputDto> getFieldErrors() {
        return fieldErrors;
    }
}
