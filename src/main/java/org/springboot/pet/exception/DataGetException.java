package org.springboot.pet.exception;

import jakarta.transaction.SystemException;

public class DataGetException extends SystemException {
    public DataGetException(String value) {
        super("При запросе в БД данные по сущности '" + value + "' не найдены.");
    }
}
