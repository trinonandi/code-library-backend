package com.codelibrary.codelibrary.auth.registration.validator;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class ValidatorForPassword implements Predicate<String> {
    @Override
    public boolean test(String s) {
        return s.matches(
                "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#.$%^&-+=()])(?=\\S+$).{8,20}$"
        );
    }
}

