import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

class StudentRepository {
    private static final Logger logger = Logger.getLogger(StudentRepository.class.getName());

    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
        logger.info("Student added: " + student.getFullName());
    }

    public void deleteStudent(String id) {
        Optional<Student> studentToDelete = students.stream().filter(student -> student.getId().equals(id)).findFirst();
        if (studentToDelete.isPresent()) {
            students.remove(studentToDelete.get());
            logger.info("Student deleted with ID: " + id);
        } else {
            throw new IllegalArgumentException("Student with ID " + id + " not found.");
        }
    }

    public List<Student> getStudentsByAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative.");
        }

        List<Student> studentsByAge = new ArrayList<>();
        for (Student student : students) {
            if (student.getAge() == age) {
                studentsByAge.add(student);
            }
        }
        return studentsByAge;
    }

    public List<Student> getAllStudents() {
        return students;
    }

    public void sortStudentsByName() {
        students.sort(Comparator.comparing(Student::getFullName));
    }

    public void sortStudentsByBirthDate() {
        students.sort(Comparator.comparing(student -> student.getBirthDate().toString()));
    }
}
