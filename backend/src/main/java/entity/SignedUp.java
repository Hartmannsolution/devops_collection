/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author root
 */
@Entity
@Table(name="signedup")
@NamedQuery(name = "SignedUp.deleteAllRows", query = "DELETE FROM SignedUp")
public class SignedUp implements Serializable {

    @ManyToOne
    private YogaClass yogaClass;

  

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="paid")
    private boolean paid;
    @Column(name="datePaid")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datePaid;
    @ManyToOne
    private Student student;

    public SignedUp() {
    }

    public SignedUp(YogaClass yogaClass, Student student, boolean paid, Date datePaid) {
        this.yogaClass = yogaClass;
        this.student = student;
        this.paid = paid;
        this.datePaid = datePaid;
    }

    public SignedUp(boolean paid, Date datePaid) {
        this.paid = paid;
        this.datePaid = datePaid;
    }
    
    public SignedUp(YogaClass yogaClass, boolean paid, Date datePaid) {
        this.yogaClass = yogaClass;
        this.paid = paid;
        this.datePaid = datePaid;
    }

    public YogaClass getYogaClass() {
        return yogaClass;
    }

    public void setYogaClass(YogaClass yogaClass) {
        this.yogaClass = yogaClass;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
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

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SignedUp)) {
            return false;
        }
        SignedUp other = (SignedUp) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.SignedUp[ id=" + id + " ]";
    }
    
}
