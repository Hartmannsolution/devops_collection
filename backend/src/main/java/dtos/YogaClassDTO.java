/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entity.YogaClass;
import java.util.List;

/**
 *
 * @author root
 */
public class YogaClassDTO {
    private Long id;
    private int maxParticipants;
    private double price;
    private List<InstructorDTO> instructors;
    private List<SignedUpDTO> signedUp;
    private CourseDTO course;


    public YogaClassDTO() {
    }

    public YogaClassDTO(YogaClass yogaClass) {
        this.id = yogaClass.getId();
        this.maxParticipants = yogaClass.getMaxParticipants();
        this.price = yogaClass.getPrice();
        this.course = new CourseDTO(yogaClass.getCourse());
        this.instructors = InstructorDTO.convertInstructorListToDTO(yogaClass.getInstructors());
    }

    public YogaClassDTO(Long id, int maxParticipants, double price) {
        this.id = id;
        this.maxParticipants = maxParticipants;
        this.price = price;
    }

    public YogaClassDTO(int maxParticipants, double price) {
        this.maxParticipants = maxParticipants;
        this.price = price;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

     public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<InstructorDTO> getInstructors() {
        return instructors;
    }

    public void setInstructors(List<InstructorDTO> instructors) {
        this.instructors = instructors;
    }

    public List<SignedUpDTO> getSignedUp() {
        return signedUp;
    }

    public void setSignedUp(List<SignedUpDTO> signedUp) {
        this.signedUp = signedUp;
    }

    public CourseDTO getCourse() {
        return course;
    }

    public void setCourse(CourseDTO course) {
        this.course = course;
    }
    
    
    
}
