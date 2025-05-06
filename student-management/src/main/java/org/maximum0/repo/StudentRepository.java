package org.maximum0.repo;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.maximum0.domain.Student;

public class StudentRepository {

  private final Map<String, Student> studentMap = new HashMap<>();

  public void save(Student student) {
    studentMap.put(student.getName(), student);
  }

  public Optional<Student> findByName(String name) {
    return Optional.ofNullable(studentMap.get(name));
  }
}
