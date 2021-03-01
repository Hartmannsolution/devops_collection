/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entity.SignedUp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author root
 */
public class SignedUpDTO {
    private Long id;
    private boolean paid;
    private Date datePaid;
    private YogaClassDTO yogaClass;
    private StudentDTO student;

    public SignedUpDTO() {
    }

    public SignedUpDTO(SignedUp signedUp) {
        this.id = signedUp.getId();
        this.paid = signedUp.isPaid();
        this.datePaid = signedUp.getDatePaid();
        this.yogaClass = new YogaClassDTO(signedUp.getYogaClass());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public Date getDatePaid() {
        return datePaid;
    }

    public void setDatePaid(Date datePaid) {
        this.datePaid = datePaid;
    }

    public YogaClassDTO getYogaClass() {
        return yogaClass;
    }

    public void setYogaClass(YogaClassDTO yogaClass) {
        this.yogaClass = yogaClass;
    }

    public StudentDTO getStudent() {
        return student;
    }

    public void setStudent(StudentDTO student) {
        this.student = student;
    }
    
    public static List<SignedUpDTO> convertSignedUpListToDTO(List<SignedUp> signedUps){
        List<SignedUpDTO> signedUpDTOS = new ArrayList<>();
        for (SignedUp signedUp : signedUps) {
            signedUpDTOS.add(new SignedUpDTO(signedUp));
        }
        return signedUpDTOS;
    }
    
}
