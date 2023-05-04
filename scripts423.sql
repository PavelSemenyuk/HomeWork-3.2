SELECT student.name, student.age, faculty.name
FROM student
         INNER JOIN generalInformation ON student.faculty_id = faculty.id

SELECT student.name, student.age, avatar.url
FROM student
         INNER JOIN avatarStudent ON student.id = avatar.student_id