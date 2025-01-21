package org.springboot.pet.exception;

import jakarta.transaction.SystemException;

public class ExistUserException extends SystemException {
    public ExistUserException(String existEmail) {
        super("Юзер с email -> '" + existEmail + "' уже существует.");
    }
}
