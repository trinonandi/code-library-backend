package com.codelibrary.codelibrary.auth.registration.validator;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class ValidatorForEmail implements Predicate<String> {

    @Override
    public boolean test(String s) {
        EmailValidator emailValidator = EmailValidator.getInstance();
        return emailValidator.isValid(s);
    }
}