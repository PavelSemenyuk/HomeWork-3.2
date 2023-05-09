package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StudentService {
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        logger.info("Был вызван метод для создания студента");
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {
        Optional<Student> studentForFind = studentRepository.findById(id);
        if (studentForFind.isEmpty()) {
            logger.error("Нет такого студента c id = " + id);
            throw new RuntimeException("Нет такого студента");
        }
        logger.info("Был вызван метод для поиска студента по id");
        return studentForFind.get();
    }

    public Student editStudent(Student student) {
        logger.info("Был вызван метод для изменения студента");
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        logger.info("Был вызван метод для удаления студента");
        studentRepository.deleteById(id);
    }

    public Collection<Student> getAll() {
        logger.info("Был вызван метод для получения списка всех студентов");
        return studentRepository.findAll();
    }

    public Collection<Student> findByAgeBetween(int age, int age2) {
        logger.info("Был вызван метод для поиска студентов определенного возраста");
        return studentRepository.findByAgeBetween(age, age2);
    }


    public long getAllCount() {
        logger.info("Был вызван метод для получения количества всех студентов");
        return studentRepository.count();
    }

    public double getAverageAge() {
        logger.info("Был вызван метод для получения среднего возраста всех студентов");
        return studentRepository.getAverageAge();
    }

    public List<Student> getStudentLastFave() {
        logger.info("Был вызван метод для получения из списка пяти последних студентов");
        return studentRepository.getStudentLastFave();
    }

    public Collection<Student> getStudentsByName(String name) {
        logger.info("Был вызван метод для получения из списка имен студентов");
        return studentRepository.getStudentsByName(name);
    }

    public Collection<String> getSortedAlphabetically() {
        Collection<String> students = studentRepository.findAll()
                .stream()
                .map(Student::getName)
                .filter(name -> name.toUpperCase().startsWith("A"))
                .sorted()
                .collect(Collectors.toList());
        logger.info("Был вызван метод для получения из списка  студентов в алфавитном порядке");
        return students;

    }

    public double averageAgeOfAllStudents() {
        double age = studentRepository.findAll()
                .stream()
                .mapToInt(Student::getAge)
                .average().orElse(0.0);
        logger.info("Был вызван метод для получения среднего возраста из списка студентов");
        return age;
    }

    public int getSum() {

        int sum = Stream.iterate(1, a -> a + 1)   // 0+1+2+3+4+5+6...
                .limit(1_000_000)              // до 1кк
                .parallel()                            // параллелим на 4 потока 0-249к; 250к-499к; 500к-749к; 750к-1000к
                .reduce(0, (a, b) -> a + b);    // 0+1, 1+2, 3+3, 6+4, 10+5... + 0=250..250+251... +  и т д

        return sum;


    }

    private void printStudent(Student student) {
        System.out.println(Thread.currentThread().getName() + " " + student);
    }

    public void printAllStudents() {
        List<Student> students = studentRepository.findAll();
        System.out.println(students);

        printStudent(students.get(0));
        printStudent(students.get(1));

        new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            printStudent(students.get(2));
            printStudent(students.get(3));
        }).start();

        new Thread(() -> {
            printStudent(students.get(4));
            printStudent(students.get(5));
        }).start();
    }

    private void printStudentSynchron(Student student) {
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + " " + student);
        }
    }

    public void printAllSynchronStudents() {
        List<Student> students = studentRepository.findAll();
        System.out.println(students);

        printStudentSynchron(students.get(0));
        printStudentSynchron(students.get(1));

        new Thread(() -> {
            printStudentSynchron(students.get(2));
            printStudentSynchron(students.get(3));
        }).start();

        new Thread(() -> {
            printStudentSynchron(students.get(4));
            printStudentSynchron(students.get(5));
        }).start();
    }


}
