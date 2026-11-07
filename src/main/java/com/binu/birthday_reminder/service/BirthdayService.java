package com.binu.birthday_reminder.service;

import com.binu.birthday_reminder.entity.Birthday;
import com.binu.birthday_reminder.exception.BirthdayNotFoundException;
import com.binu.birthday_reminder.exception.DuplicateBirthdayException;
import com.binu.birthday_reminder.repository.BirthdayRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Service
public class BirthdayService {

    private final BirthdayRepository birthdayRepository;

    public BirthdayService(BirthdayRepository birthdayRepository) {
        this.birthdayRepository = birthdayRepository;
    }

    public Birthday addBirthday(Birthday birthday) {

        boolean exists = birthdayRepository.existsByNameIgnoreCaseAndDateOfBirth(
                birthday.getName(),
                birthday.getDateOfBirth());

        if (exists) {
            throw new DuplicateBirthdayException(birthday.getName());
        }

        return birthdayRepository.save(birthday);
    }

    public List<Birthday> getAllBirthdays() {
        return birthdayRepository.findAll();
    }

    public Birthday getBirthdayById(Long id) {
        return birthdayRepository.findById(id)
                .orElseThrow(() -> new BirthdayNotFoundException(id));
    }

    public void deleteBirthday(Long id) {
        Birthday birthday = birthdayRepository.findById(id)
                .orElseThrow(() -> new BirthdayNotFoundException(id));

        birthdayRepository.delete(birthday);
    }

    public List<Birthday> getBirthdaysToday() {
        LocalDate today = LocalDate.now(
                ZoneId.of("Asia/Colombo"));

        return birthdayRepository.findBirthdaysByMonthAndDay(
                today.getMonthValue(),
                today.getDayOfMonth());
    }

    public Birthday updateBirthday(Long id, Birthday updatedBirthday) {

        // Check whether the birthday exists
        Birthday existingBirthday = birthdayRepository.findById(id)
                .orElseThrow(() -> new BirthdayNotFoundException(id));

        // Check for duplicate birthdays
        boolean duplicateExists = birthdayRepository.existsByNameIgnoreCaseAndDateOfBirth(
                updatedBirthday.getName(),
                updatedBirthday.getDateOfBirth());

        if (duplicateExists &&
                !(existingBirthday.getName().equalsIgnoreCase(
                        updatedBirthday.getName()) &&
                        existingBirthday.getDateOfBirth().equals(
                                updatedBirthday.getDateOfBirth()))) {

            throw new DuplicateBirthdayException(
                    "A birthday with this name and date already exists");
        }

        // Update existing record
        existingBirthday.setName(updatedBirthday.getName());
        existingBirthday.setDateOfBirth(updatedBirthday.getDateOfBirth());

        return birthdayRepository.save(existingBirthday);
    }

}