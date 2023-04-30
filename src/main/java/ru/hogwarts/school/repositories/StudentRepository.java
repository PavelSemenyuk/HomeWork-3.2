package ru.hogwarts.school.repositories;

import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Collection<Student> findByAgeBetween(int age, int age2);

    @Query("select avg(s.age) " +
            "from Student s")
    double getAverageAge();


    @Query(value = "SELECT * " +
            "FROM student" +
            "DRDER BY id DESC" +
            "LIMIT 5",
            nativeQuery = true)
    List<Student> getStudentLastFave();
}
