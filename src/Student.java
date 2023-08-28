import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

class Student {
    private static final Logger logger = Logger.getLogger(Student.class.getName());

    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String gender;
    private String id;

    public Student(String firstName, String lastName, String birthDateStr, String gender, String id) throws IllegalArgumentException {
        validateName(firstName, "First Name");
        validateName(lastName, "Last Name");
        validateBirthDate(birthDateStr);
        validateGender(gender);
        validateId(id);

        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = LocalDate.parse(birthDateStr, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        this.gender = gender.toUpperCase();
        this.id = id;

        logger.info("Student created: " + firstName + " " + lastName);
    }

    private void validateName(String name, String field) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(field + " cannot be empty.");
        }
    }

    private void validateBirthDate(String birthDateStr) {
        LocalDate currentDate = LocalDate.now();
        LocalDate minBirthDate = currentDate.minusYears(18).minusDays(1);
        LocalDate maxBirthDate = currentDate.minusYears(100);

        LocalDate parsedBirthDate = LocalDate.parse(birthDateStr, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        if (parsedBirthDate.isBefore(maxBirthDate) || parsedBirthDate.isAfter(minBirthDate)) {
            throw new IllegalArgumentException("Invalid birth date. Must be between " +
                    maxBirthDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + " and " +
                    minBirthDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + ".");
        }
    }

    private void validateGender(String gender) {
        if (!gender.equalsIgnoreCase("M") && !gender.equalsIgnoreCase("F") &&
                !gender.equalsIgnoreCase("Masculin") && !gender.equalsIgnoreCase("Feminin")) {
            throw new IllegalArgumentException("Invalid gender. Must be 'M' or 'F'.");
        }
    }

    private void validateId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID cannot be empty.");
        }
    }

    public int getAge() {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", gender='" + gender + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getId() {
        return id;
    }
}
