package com.binu.birthday_reminder.controller;

import com.binu.birthday_reminder.entity.Birthday;
import com.binu.birthday_reminder.service.BirthdayService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/birthdays")
public class BirthdayController {

    private final BirthdayService birthdayService;

    public BirthdayController(BirthdayService birthdayService) {
        this.birthdayService = birthdayService;
    }

    // Add a new birthday
    @PostMapping
    public ResponseEntity<Birthday> addBirthday(
            @Valid @RequestBody Birthday birthday) {

        Birthday savedBirthday = birthdayService.addBirthday(birthday);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedBirthday);
    }

    // Get all birthdays
    @GetMapping
    public ResponseEntity<List<Birthday>> getAllBirthdays() {
        return ResponseEntity.ok(
                birthdayService.getAllBirthdays());
    }

    // Get a birthday by ID
    @GetMapping("/{id}")
    public ResponseEntity<Birthday> getBirthdayById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                birthdayService.getBirthdayById(id));
    }

    // Delete a birthday
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBirthday(
            @PathVariable Long id) {

        birthdayService.deleteBirthday(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/today")
    public ResponseEntity<List<Birthday>> getBirthdaysToday() {
        return ResponseEntity.ok(
                birthdayService.getBirthdaysToday());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Birthday> updateBirthday(
            @PathVariable Long id,
            @Valid @RequestBody Birthday birthday) {

        Birthday updatedBirthday = birthdayService.updateBirthday(id, birthday);

        return ResponseEntity.ok(updatedBirthday);
    }

}
