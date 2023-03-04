package edu.sabanciuniv.test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import edu.sabanciuniv.model.School;
import edu.sabanciuniv.model.Student;
import edu.sabanciuniv.utility.EntityManagerUtils;

import java.util.ArrayList;
import java.util.List;

public class SchoolStudentsTest {
    public static void main(String[] args) {

        Student student1 =
                new Student("Yusuf İslam","Tuncer","Türkiye","y.islam9920@gmail.com");
        Student student2 =
                new Student("Ahmet","Mehmet","Kastamonu","amehmet@gmail.com");
        Student student3 =
                new Student("Ali","Veli","Konya","aliveli@gmail.com");
        Student student4 =
                new Student("Hasan","Hüseyin","Isparta","hhüseyin@gmail.com");
        Student student5 =
                new Student("Ayşe","Fatma","Adana","afatma@gmail.com");


        School school1 = new School("YTÜ",35000,"Yıldız/İstanbul");
        School school2 = new School("İTÜ",30000,"Ayazağa/İstanbul");
        School school3 = new School("BOUN",15000,"Hisarüstü/İstanbul");

        student1.setSchool(school1);
        student2.setSchool(school2);
        student3.setSchool(school1);
        student4.setSchool(school2);
        student5.setSchool(school3);

        List<Student> studentList = new ArrayList<>();
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);
        studentList.add(student4);
        studentList.add(student5);

        List<School> schoolList = new ArrayList<>();
        schoolList.add(school1);
        schoolList.add(school2);
        schoolList.add(school3);

        EntityManager entityManager = EntityManagerUtils.getEntityManager("mysqlPU");

         //saveSchools(entityManager, schoolList);
         //saveStudents(entityManager, studentList);
        //findAllSchools(entityManager);
        // findAllStudents(entityManager);
        // findSchoolsByNames(entityManager, school1.getName());
        // findStudentsByNames(entityManager, student2.getFirstName(), student2.getLastName());
        // updateStudentAddress(entityManager, student1.getAddress(), "Ankara");
        deleteStudentsByNames(entityManager, student2.getFirstName(), student2.getLastName());
    }

    private static void deleteStudentsByNames(EntityManager entityManager, String firstName, String lastName) {
        entityManager.getTransaction().begin();

        Student foundStudent =  entityManager.createQuery("from Student s where s.firstName = ?1 and s.lastName = ?2", Student.class)
                .setParameter(1, firstName)
                .setParameter(2, lastName)
                .getSingleResult();

        entityManager.remove(foundStudent);

        entityManager.getTransaction().commit();
    }

    private static void updateStudentAddress(EntityManager entityManager, String address, String newAddress) {
        entityManager.getTransaction().begin();

        Student foundStudent = entityManager.createQuery("from Student s where s.address = ?1", Student.class)
                .setParameter(1, address)
                .getSingleResult();

        foundStudent.setAddress(newAddress);
        entityManager.merge(foundStudent);

        entityManager.getTransaction().commit();
    }

    private static void findStudentsByNames(EntityManager entityManager, String firstName, String lastName) {
        List<Student> studentList =   entityManager.createQuery("from Student s where s.firstName = ?1 and s.lastName = ?2", Student.class)
                .setParameter(1, firstName)
                .setParameter(2, lastName)
                .getResultList();

        for (Student student: studentList) {
            System.out.println(studentList);
        }
    }


    private static void findSchoolsByNames(EntityManager entityManager, String name) {
        List<School> schoolList = entityManager.createQuery("from School s where s.name = ?1", School.class)
                .setParameter(1, name)
                .getResultList();
        for (School school : schoolList) {
            System.out.println(school);
        }
    }

    private static void findAllStudents(EntityManager entityManager) {
        TypedQuery x =  entityManager.createQuery("from Student s", Student.class );
        List<Student> studentList =  x.getResultList();

        for (Student student : studentList) {
            System.out.println(student);
        }
    }

    private static void findAllSchools(EntityManager entityManager) {
        TypedQuery x =  entityManager.createQuery("from School s", School.class );
        List<School> schoolList =  x.getResultList();

        for (School school : schoolList) {
            System.out.println(school);
        }
    }

    private static void saveSchools(EntityManager entityManager, List<School> schoolList) {
        try {
            entityManager.getTransaction().begin();
            for (School school : schoolList) {
                entityManager.persist(school);
            }
            entityManager.getTransaction().commit();
        }
        catch (Exception e) {
            entityManager.getTransaction().rollback();
        }

    }


    private static void saveStudents(EntityManager entityManager, List<Student> studentList) {
        try {
            entityManager.getTransaction().begin();
            for (Student student : studentList) {
                entityManager.persist(student);
            }
            entityManager.getTransaction().commit();
        }
        catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
        finally {
            EntityManagerUtils.closeEntityManager(entityManager);
        }
    }









}

