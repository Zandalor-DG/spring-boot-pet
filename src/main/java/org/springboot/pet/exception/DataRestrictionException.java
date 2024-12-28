package org.springboot.pet.exception;

import jakarta.transaction.SystemException;

public class DataRestrictionException extends SystemException {
    public DataRestrictionException(String column, String condition, long value) {
        super(String.format(
                "Не удалось сохранить данные в БД, Из за ограничения поля '%s' %s %s",
                column,
                condition,
                value
        ));
    }

    public DataRestrictionException(String entity, String column, String restriction) {
        super(String.format(
                "Не удалось сохранить данные сущности '%s' в БД. Из за ограничения колонки '%s' : '%s'",
                entity,
                column,
                restriction
        ));
    }
}
