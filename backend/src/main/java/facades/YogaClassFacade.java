/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.InstructorDTO;
import dtos.YogaClassDTO;
import entity.Course;
import entity.Instructor;
import entity.YogaClass;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author root
 */
public class YogaClassFacade {
    private static EntityManagerFactory emf;
    private static YogaClassFacade instance;

    private YogaClassFacade() {
    }

    /**
     *
     * @param _emf
     * @return the instance of this facade.
     */
    public static YogaClassFacade getYogaClassFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new YogaClassFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public List<YogaClassDTO> getAllYogaClasses(){
        List<YogaClassDTO> listOfYogaClassDTO = new ArrayList<>();
        EntityManager em = getEntityManager();
        try {
            List<YogaClass> listOfYogaClass = em.createQuery("SELECT y From YogaClass y", YogaClass.class).getResultList();
            for (YogaClass yogaClass : listOfYogaClass) {
                listOfYogaClassDTO.add(new YogaClassDTO(yogaClass));
            }
            return listOfYogaClassDTO;
        } finally {
            em.close();
        }
    }
    
    public YogaClassDTO createYogaClass(YogaClassDTO yogaClassDTO){
        EntityManager em = getEntityManager();
        try {
            Course course = em.find(Course.class, yogaClassDTO.getCourse().getId());
            List<Instructor> instructors = new ArrayList<>();
            for (InstructorDTO instructor : yogaClassDTO.getInstructors()) {
                instructors.add(em.find(Instructor.class, instructor.getId()));
            }
            YogaClass yogaClass = new YogaClass(course, yogaClassDTO.getMaxParticipants(), yogaClassDTO.getPrice(), instructors);
            em.getTransaction().begin();
            em.persist(yogaClass);
            em.getTransaction().commit();
            return new YogaClassDTO(yogaClass);
        } finally {
            em.close();
        }
    }

    public YogaClassDTO deleteYogaClass(YogaClassDTO yogaClassDTO) {
        EntityManager em = getEntityManager();
        try {
            YogaClass yogaClass = em.find(YogaClass.class, yogaClassDTO.getId());
            em.getTransaction().begin();
            em.remove(yogaClass);
            em.getTransaction().commit();
            return new YogaClassDTO(yogaClass);
        } finally {
            em.close();
        }
    }

    public YogaClassDTO editYogaClass(YogaClassDTO yogaClassDTO) {
        EntityManager em = getEntityManager();
        try {
            YogaClass yogaClass = em.find(YogaClass.class, yogaClassDTO.getId());
            em.getTransaction().begin();
            yogaClass.setMaxParticipants(yogaClassDTO.getMaxParticipants());
            yogaClass.setPrice(yogaClassDTO.getPrice());
            em.persist(yogaClass);
            em.getTransaction().commit();
            return new YogaClassDTO(yogaClass);
        } finally {
            em.close();
        }
    }
}
