package org.springboot.pet.exception;

import jakarta.transaction.SystemException;

public class PasswordConfirmationFailed extends SystemException {
    public PasswordConfirmationFailed() {
        super("Пароль и подтверждение пароля, не совпадают");
    }
}
