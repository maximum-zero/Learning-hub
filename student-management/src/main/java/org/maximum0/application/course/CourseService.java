package org.maximum0.application.course;

import java.util.ArrayList;
import java.util.List;
import org.maximum0.application.course.dto.CourseInfoDto;
import org.maximum0.application.student.StudentService;
import org.maximum0.domain.Course;
import org.maximum0.domain.DayOfWeek;
import org.maximum0.domain.Student;
import org.maximum0.repo.CourseRepository;

public class CourseService {
  private final CourseRepository courseRepository;
  private final StudentService studentService;

  public CourseService(CourseRepository courseRepository, StudentService studentService) {
    this.courseRepository = courseRepository;
    this.studentService = studentService;
  }

  public void registerCourse(CourseInfoDto courseInfoDto) {
    Student student = studentService.getStudent(courseInfoDto.getStudentName());
    Course course = new Course(student, courseInfoDto.getCourseName(), courseInfoDto.getFee(), courseInfoDto.getDayOfWeek(), courseInfoDto.getCourseTime());
    courseRepository.save(course);
  }

  public List<CourseInfoDto> getCourseDayOfWeek(DayOfWeek dayOfWeek) {
    // TODO: 과제 구현 부분
    return new ArrayList<>();
  }

  public void changeFee(String studentName, int fee) {
    // TODO: 과제 구현 부분
  }
}
