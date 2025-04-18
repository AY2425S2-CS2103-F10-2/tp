package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Person's birthday in the ClientNest.
 * Guarantees: immutable; is valid as declared in {@link #isValidBirthday(String)}
 */
public class Birthday {

    public static final String MESSAGE_CONSTRAINTS =
            "Please enter a valid birthday in the format YYYY-MM-DD. "
                    + "It must be a past date and not earlier than the year 1900.";

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static final int MIN_YEAR = 1900;

    public final LocalDate value;

    /**
     * Constructs a {@code Birthday}.
     *
     * @param birthday A valid birthday string.
     */
    public Birthday(String birthday) {
        requireNonNull(birthday);
        checkArgument(isValidBirthday(birthday), MESSAGE_CONSTRAINTS);
        this.value = LocalDate.parse(birthday, FORMATTER);
    }

    /**
     * Returns the {@code LocalDate} representation of the birthday.
     *
     * @return LocalDate value of this birthday.
     */
    public LocalDate getValue() {
        return value;
    }

    /**
     * Returns true if given string is a valid birthday.
     */
    public static boolean isValidBirthday(String test) {
        try {
            LocalDate date = LocalDate.parse(test, FORMATTER);
            int year = date.getYear();
            return year >= MIN_YEAR && !date.isAfter(LocalDate.now());
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return value.format(FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Birthday)) {
            return false;
        }

        Birthday otherBirthday = (Birthday) other;
        return value.equals(otherBirthday.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    /**
     * Returns true if the birthday is within the next 30 days from today.
     */
    public boolean isWithinNext30Days() {
        LocalDate now = LocalDate.now();
        LocalDate in30Days = now.plusDays(30);

        // Birthday this year
        LocalDate thisYearsBirthday = value.withYear(now.getYear());

        // Handle birthday passed already this year (check next year's birthday)
        if (thisYearsBirthday.isBefore(now)) {
            thisYearsBirthday = thisYearsBirthday.plusYears(1);
        }

        return !thisYearsBirthday.isAfter(in30Days);
    }
}
