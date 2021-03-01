/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entity.Course;
import java.util.List;

/**
 *
 * @author root
 */
public class CourseDTO {

    private long id;
    private String courseName;
    private String description;
    private List<YogaClassDTO> yogaClass;

    public CourseDTO() {
    }

    public CourseDTO(long id, String courseName, String description) {
        this.id = id;
        this.courseName = courseName;
        this.description = description;
    }
    
    public CourseDTO(Course course) {
        this.id = course.getId();
        this.courseName = course.getCourseName();
        this.description = course.getDescription();
    }

    public CourseDTO(String courseName, String description) {
        this.courseName = courseName;
        this.description = description;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<YogaClassDTO> getYogaClass() {
        return yogaClass;
    }

    public void setYogaClass(List<YogaClassDTO> yogaClass) {
        this.yogaClass = yogaClass;
    }
    
}
