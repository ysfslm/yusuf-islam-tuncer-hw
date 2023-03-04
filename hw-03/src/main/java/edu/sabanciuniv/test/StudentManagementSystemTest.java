package edu.sabanciuniv.test;

import jakarta.persistence.EntityManager;
import edu.sabanciuniv.model.*;
import edu.sabanciuniv.utility.EntityManagerUtils;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;


public class StudentManagementSystemTest {

    public static void main(String[] args) {

        Student student1 =
                new Student("Hasan Hüseyin", LocalDate.of(1999, Month.JANUARY, 30),"Beşiktaş/İstanbul",Gender.MALE);

        Student student2 =
                new Student("Ahmet Çakar", LocalDate.of(1998, Month.APRIL, 23),"Kartal/İstanbul", Gender.MALE);

        Student student3 =
                new Student("Ali Veli", LocalDate.of(1997, Month.AUGUST, 4), "Üsküdar/İstanbul", Gender.MALE);

        Student student4 =
                new Student("Ayşe Fatma", LocalDate.of(1998, Month.FEBRUARY, 9), "Kadıköy/İstanbul", Gender.FEMALE);

        Student student5 =
                new Student("Deniz Işın", LocalDate.of(1996, Month.JUNE, 21), "Bakırköy/İstanbul", Gender.FEMALE);


        List<Student> studentArrayList = new ArrayList<>();

        studentArrayList.add(student1);
        studentArrayList.add(student2);
        studentArrayList.add(student3);
        studentArrayList.add(student4);
        studentArrayList.add(student5);

        /////////////////////////////////////////////////////////////////////////////////////////////////////


        Course course1 =
                new Course("Enterprise Java Frameworks","IT526", 3);
        Course course2 =
                new Course("Software Testing","IT38", 3);
        Course course3 =
                new Course("Machine Learning", "IT541", 3);

        List<Course> courseList = new ArrayList<>();
        courseList.add(course1);
        courseList.add(course2);
        courseList.add(course3);

        /////////////////////////////////////////////////////////////////////////////////////////////////////

        Instructor instructor1 =
                new VisitingResearcher("Namık Kemal", "Renan", "05555555555", 1000l);

        Instructor instructor2 =
                new PermanentInstructor("Kemal Derviş", "Chicago", "01111111111", 10000000l);

        Instructor instructor3 =
                new Instructor("Erdem Beyazıt", "Kahramanmaraş", "02222222222");

        List<Instructor> instructorList = new ArrayList<>();
        instructorList.add(instructor1);
        instructorList.add(instructor2);
        instructorList.add(instructor3);

        /////////////////////////////////////////////////////////////////////////////////////////////////////

        course1.getStudentList().add(student1);
        course1.getStudentList().add(student2);
        course2.getStudentList().add(student4);
        course2.getStudentList().add(student5);
        course3.getStudentList().add(student3);
        course1.getStudentList().add(student5);
        course3.getStudentList().add(student4);

        /////////////////////////////////////////////////////////////////////////////////////////////////////

        course1.setInstructor(instructor1);
        course2.setInstructor(instructor2);
        course3.setInstructor(instructor2);

        /////////////////////////////////////////////////////////////////////////////////////////////////////

        EntityManager entityManager = EntityManagerUtils.getEntityManager("mysqlPU");

        try {
            persistElementsOfArray(entityManager, studentArrayList);
            persistElementsOfArray(entityManager, instructorList);
            persistElementsOfArray(entityManager, courseList);

        }

        catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    private static <E> void persistElementsOfArray(EntityManager entityManager, List<E> givenList) {
        entityManager.getTransaction().begin();
        for (E e : givenList) {
            entityManager.persist(e);
        }
        entityManager.getTransaction().commit();
    }
}

