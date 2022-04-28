package pl.edu.pwr.classfinancemanager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomDate {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static LocalDate parse(String value) {
        return LocalDate.parse(value, FORMATTER);
    }
}
