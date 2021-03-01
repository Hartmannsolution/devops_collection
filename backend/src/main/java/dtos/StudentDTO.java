/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entity.Student;
import java.util.List;

/**
 *
 * @author root
 */
public class StudentDTO {
    private long id;
    private String name;
    private int phone;
    private String email;
    private List<SignedUpDTO> signedUp;

    public StudentDTO() {
    }

    public StudentDTO(Student student) {
        this.id = student.getId();
        this.name = student.getName();
        this.phone = student.getPhone();
        this.email = student.getEmail();
        this.signedUp = SignedUpDTO.convertSignedUpListToDTO(student.getSignedUp());
    }

    public StudentDTO(long id, String name, int phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
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

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<SignedUpDTO> getSignedUp() {
        return signedUp;
    }

    public void setSignedUp(List<SignedUpDTO> signedUp) {
        this.signedUp = signedUp;
    }
    
}
