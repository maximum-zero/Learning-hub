package org.maximum0.application.student;

import org.maximum0.application.student.dto.StudentInfoDto;
import org.maximum0.domain.Student;
import org.maximum0.repo.StudentRepository;

public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void saveStudent(StudentInfoDto studentInfoDto) {
        Student student = new Student(studentInfoDto.getName(), studentInfoDto.getAge(), studentInfoDto.getAddress());
        studentRepository.save(student);
    }

    public Student getStudent(String name) {
        return studentRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 학생이 없습니다."));
    }

    public void activateStudent(String name) {
        // TODO: 과제 구현 부분
    }

    public void deactivateStudent(String name) {
        // TODO: 과제 구현 부분
    }
}
