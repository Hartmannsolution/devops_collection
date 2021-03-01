/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entity.Instructor;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author root
 */
public class InstructorDTO {
    private long id;
    private String name;
    private List<YogaClassDTO> yogaClass;


    public InstructorDTO() {
    }

    public InstructorDTO(Instructor instructor) {
        this.id = instructor.getId();
        this.name = instructor.getName();
    }

    public InstructorDTO(String name) {
        this.name = name;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<YogaClassDTO> getYogaClass() {
        return yogaClass;
    }

    public void setYogaClass(List<YogaClassDTO> yogaClass) {
        this.yogaClass = yogaClass;
    }
    
    public static List<InstructorDTO> convertInstructorListToDTO(List<Instructor> instructors){
        List<InstructorDTO> instructorDTOs = new ArrayList<>();
        for (Instructor instructor : instructors) {
            instructorDTOs.add(new InstructorDTO(instructor));
        }
        return instructorDTOs;
    }
      
}
