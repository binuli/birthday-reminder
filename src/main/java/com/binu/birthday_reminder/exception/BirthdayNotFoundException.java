package com.binu.birthday_reminder.exception;

public class BirthdayNotFoundException extends RuntimeException {

    public BirthdayNotFoundException(Long id) {
        super("Birthday not found with ID: " + id);
    }
}