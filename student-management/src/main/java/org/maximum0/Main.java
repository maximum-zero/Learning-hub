package org.maximum0;

import org.maximum0.application.course.CourseService;
import org.maximum0.application.student.StudentService;
import org.maximum0.repo.CourseRepository;
import org.maximum0.repo.StudentRepository;
import org.maximum0.ui.UserInputType;
import org.maximum0.ui.course.CourseController;
import org.maximum0.ui.course.CoursePresenter;
import org.maximum0.ui.student.StudentController;
import org.maximum0.ui.student.StudentPresenter;

public class Main {

    public static void main(String[] args) {
        StudentRepository studentRepository = new StudentRepository();
        CourseRepository courseRepository = new CourseRepository();

        StudentService studentService = new StudentService(studentRepository);
        CourseService courseService = new CourseService(courseRepository, studentService);

        CoursePresenter coursePresenter = new CoursePresenter();
        StudentPresenter studentPresenter = new StudentPresenter();

        CourseController courseController = new CourseController(coursePresenter, courseService, studentPresenter);
        StudentController studentController = new StudentController(studentPresenter, studentService);

        studentPresenter.showMenu();
        UserInputType userInputType = studentController.getUserInput();
        while (userInputType != UserInputType.EXIT) {
            switch (userInputType) {
                case NEW_STUDENT:
                    studentController.registerStudent();
                    break;
                case NEW_COURSE:
                    courseController.registerCourse();
                    break;
                case SHOW_COURSE_DAY_OF_WEEK:
                    courseController.showCourseDayOfWeek();
                    break;
                case ACTIVATE_STUDENT:
                    studentController.activateStudent();
                    break;
                case DEACTIVATE_STUDENT:
                    studentController.deactivateStudent();
                    break;
                case CHANGE_FEE:
                    courseController.changeFee();
                    break;
                default:
                    studentPresenter.showErrorMessage();
                    break;
            }
            studentPresenter.showMenu();
            userInputType = studentController.getUserInput();
        }
    }
}