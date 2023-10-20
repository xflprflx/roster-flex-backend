package com.rosterflex.application.services.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<PasswordValid, String> {
    @Override
    public void initialize(PasswordValid constraintAnnotation) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null) {
            return false;
        }

        // Pelo menos 8 caracteres
        if (password.length() < 8) {
            return false;
        }

        // Pelo menos 1 letra maiúscula
        if (!Pattern.compile("[A-Z]").matcher(password).find()) {
            return false;
        }

        // Pelo menos 1 letra minúscula
        if (!Pattern.compile("[a-z]").matcher(password).find()) {
            return false;
        }

        // Pelo menos 1 número
        if (!Pattern.compile("[0-9]").matcher(password).find()) {
            return false;
        }

        // Pelo menos 1 caractere especial (pode adicionar mais caracteres, se necessário)
        if (!Pattern.compile("[!@#$%^&*]").matcher(password).find()) {
            return false;
        }

        return true;
    }
}
