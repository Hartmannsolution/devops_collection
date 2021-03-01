/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.InstructorDTO;
import entity.Instructor;
import errorhandling.CourseException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author root
 */
public class InstructorFacade {
    private static EntityManagerFactory emf;
    private static InstructorFacade instance;

    private InstructorFacade() {
    }

    /**
     *
     * @param _emf
     * @return the instance of this facade.
     */
    public static InstructorFacade getInstructorFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new InstructorFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public List<InstructorDTO> getAllInstructors(){
        List<InstructorDTO> listOfInstructorDTO = new ArrayList<>();
        EntityManager em = getEntityManager();
        try {
            List<Instructor> listOfInstructors = em.createQuery("SELECT i FROM Instructor i", Instructor.class).getResultList();
            for (Instructor instructor : listOfInstructors) {
                listOfInstructorDTO.add(new InstructorDTO(instructor));
            }
            return listOfInstructorDTO;
        } finally {
            em.close();
        }
                
    }
    
    public InstructorDTO createInstructor(InstructorDTO instructorDTO) {
        EntityManager em = getEntityManager();
        try {
            Instructor instructor = new Instructor(instructorDTO.getName());
            em.getTransaction().begin();
            em.persist(instructor);
            em.getTransaction().commit();
            return new InstructorDTO(instructor);
        } finally {
            em.close();
        }
    }

    public InstructorDTO editInstructorByDTO(InstructorDTO instructorDTO) {
        EntityManager em = getEntityManager();
        try {
            Instructor instructor = em.find(Instructor.class, instructorDTO.getId());
            em.getTransaction().begin();
            instructor.setName(instructorDTO.getName());
            em.getTransaction().commit();
            return new InstructorDTO(instructor);
        } finally {
            em.close();
        }
    }

    public InstructorDTO deleteInstructorById(long id) throws CourseException {
        EntityManager em = getEntityManager();
        try {
            Instructor instructor = em.find(Instructor.class, id);
            em.getTransaction().begin();
            em.remove(instructor);
            em.getTransaction().commit();
            return new InstructorDTO(instructor);
        } catch (IllegalArgumentException e) {
            throw new CourseException("Could not find instructor to delete");
        } finally {
            em.close();
        }
    }
}
