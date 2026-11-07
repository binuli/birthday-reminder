package com.binu.birthday_reminder.exception;

public class DuplicateBirthdayException extends RuntimeException {

    public DuplicateBirthdayException(String name) {
        super("A birthday record already exists for: " + name);
    }
}