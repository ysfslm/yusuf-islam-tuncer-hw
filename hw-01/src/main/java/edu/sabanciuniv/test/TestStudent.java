package edu.sabanciuniv.test;

import jakarta.persistence.EntityManager;
import edu.sabanciuniv.model.Student;
import edu.sabanciuniv.utility.EntityManagerUtils;

import java.util.ArrayList;
import java.util.List;

public class TestStudent {

    public static void main(String[] args) {

        Student student1 =
                new Student("Yusuf İslam","Tuncer","Türkiye","y.islam9920@gmail.com");
        Student student2 =
                new Student("Ricardo","Quaresma","Portekiz","q7@gmail.com");
        Student student3 =
                new Student("Anderson","Talisca","Brezilya","andersonTalisca@gmail.com");
        Student student4 =
                new Student("Ryan","Babel","Hollanda","ryanBabel@gmail.com");
        Student student5 =
                new Student("Vincent","Aboubakar","Kamerun","abou@gmail.com");

        EntityManager entityManager = EntityManagerUtils.getEntityManager("mysqlPU");

        List<Student> studentList = new ArrayList<>();
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);
        studentList.add(student4);
        studentList.add(student5);

        System.out.println(studentList);

        saveStudents(entityManager, studentList);

    }


    private static void saveStudents(EntityManager entityManager, List<Student> studentList) {
        try {
            entityManager.getTransaction().begin();
            for (Student student : studentList) {
                entityManager.persist(student);
            }
            entityManager.getTransaction().commit();
            System.out.println("All students persisted successfully.");
        }
        catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
        finally {
            EntityManagerUtils.closeEntityManager(entityManager);
        }

    }

}


