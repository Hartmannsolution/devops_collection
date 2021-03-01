/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.CourseDTO;
import dtos.YogaClassDTO;
import entity.Course;
import entity.YogaClass;
import errorhandling.CourseException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;

/**
 *
 * @author root
 */
public class CourseFacade {

    private static EntityManagerFactory emf;
    private static CourseFacade instance;

    private CourseFacade() {
    }

    /**
     *
     * @param _emf
     * @return the instance of this facade.
     */
    public static CourseFacade getCourseFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CourseFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<CourseDTO> getAllCourses() {
        List<CourseDTO> listOfCourseDTOs = new ArrayList<>();
        EntityManager em = getEntityManager();
        try {
            List<Course> listOfCourses = em.createQuery("SELECT c FROM Course c", Course.class).getResultList();
            for (Course course : listOfCourses) {
                List<YogaClassDTO> listOfYogaClassDTOs = new ArrayList<>();
                for (YogaClass yogaclass : course.getYogaClasses()) {
                    listOfYogaClassDTOs.add(new YogaClassDTO(yogaclass));
                }
                CourseDTO courseDTO = new CourseDTO(course);
                courseDTO.setYogaClass(listOfYogaClassDTOs);
                listOfCourseDTOs.add(courseDTO);
            }
        } finally {
            em.close();
        }
        return listOfCourseDTOs;
    }

    public CourseDTO createCourse(CourseDTO courseDTO) {
        EntityManager em = getEntityManager();
        try {
            return findCourseByName(courseDTO.getCourseName());
        } catch (CourseException e) {
            Course course = new Course(courseDTO.getCourseName(), courseDTO.getDescription());
            em.getTransaction().begin();
            em.persist(course);
            em.getTransaction().commit();
            return new CourseDTO(course);
        } finally {
            em.close();
        }
    }

    public CourseDTO findCourseByName(String name) throws CourseException {
        EntityManager em = getEntityManager();
        try {
            Course course = em.createQuery("SELECT c FROM Course c WHERE c.courseName = :name", Course.class).setParameter("name", name).getSingleResult();
            return new CourseDTO(course);
        } catch (NoResultException e) {
            throw new CourseException("Could not find course");
        } finally {
            em.close();
        }
    }

    public CourseDTO findCourseById(long id) throws CourseException {
        EntityManager em = getEntityManager();
        try {
            Course course = em.find(Course.class, id);
            return new CourseDTO(course);
        } catch (NullPointerException e) {
            throw new CourseException("Could not find course");
        } finally {
            em.close();
        }
    }

    public CourseDTO editCourse(CourseDTO courseDTO) {
        EntityManager em = getEntityManager();
        try {
            Course course = em.find(Course.class, courseDTO.getId());
            em.getTransaction().begin();
            course.setCourseName(courseDTO.getCourseName());
            course.setDescription(courseDTO.getDescription());
            em.getTransaction().commit();
            return new CourseDTO(course);
        } finally {
            em.close();
        }
    }

    public CourseDTO deleteCourseById(long id) throws CourseException {
        EntityManager em = getEntityManager();
        try {
            Course course = em.find(Course.class, id);
            em.getTransaction().begin();
            em.remove(course);
            em.getTransaction().commit();
            return new CourseDTO(course);
        } catch (IllegalArgumentException e) {
            throw new CourseException("Could not find course to delete");
        } finally {
            em.close();
        }
    }

}
