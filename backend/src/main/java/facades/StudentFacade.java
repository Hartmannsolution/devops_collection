/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.StudentDTO;
import entity.Student;
import errorhandling.UserException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author root
 */
public class StudentFacade {

    private static EntityManagerFactory emf;
    private static StudentFacade instance;

    private StudentFacade() {
    }

    /**
     *
     * @param _emf
     * @return the instance of this facade.
     */
    public static StudentFacade getStudentFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new StudentFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public StudentDTO findStudentById(long id) throws UserException{
        EntityManager em = getEntityManager();
        try {
            Student student = em.find(Student.class, id);
            if(student == null) throw new UserException("Student not found");
            return new StudentDTO(student);
        } finally {
            em.close();
        }
    }
    
    public StudentDTO findStudentByName(String name) throws UserException{
        EntityManager em = getEntityManager();
        try {
            Student student = em.createQuery("SELECT s FROM Student s WHERE s.name = :name", Student.class).setParameter("name", name).getSingleResult();
            return new StudentDTO(student);
        } finally {
            em.close();
        }
    }
}
