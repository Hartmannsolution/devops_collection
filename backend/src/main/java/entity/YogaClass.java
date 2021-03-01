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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author root
 */
@Entity
@Table(name="yogaclass")
@NamedQuery(name = "YogaClass.deleteAllRows", query = "DELETE FROM YogaClass")
public class YogaClass implements Serializable {

    
    @ManyToOne
    private Course course;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="maxParticipants")
    private int maxParticipants;
    @Column(name="startDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Column(name="price")
    private double price;
    
    @ManyToMany
    private List<Instructor> instructors;
    
    @OneToMany(mappedBy = "yogaClass")
    private List<SignedUp> signedUp;

    public YogaClass() {
    }

    public YogaClass(Course course, int maxParticipants, Date startDate, double price, List<Instructor> instructors, List<SignedUp> signedUp) {
        this.course = course;
        this.maxParticipants = maxParticipants;
        this.startDate = startDate;
        this.price = price;
        this.instructors = instructors;
        this.signedUp = signedUp;
    }

    public YogaClass(int maxParticipants, Date startDate, double price) {
        this.maxParticipants = maxParticipants;
        this.startDate = startDate;
        this.price = price;
    }

    public YogaClass(Course course, int maxParticipants, Date startDate, double price, List<Instructor> instructors) {
        this.course = course;
        this.maxParticipants = maxParticipants;
        this.startDate = startDate;
        this.price = price;
        this.instructors = instructors;
    }
    
    public YogaClass(Course course, int maxParticipants, double price, List<Instructor> instructors) {
        this.course = course;
        this.maxParticipants = maxParticipants;
        this.price = price;
        this.instructors = instructors;
    }
    
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Instructor> getInstructors() {
        return instructors;
    }

    public void setInstructors(List<Instructor> instructors) {
        this.instructors = instructors;
    }

    public List<SignedUp> getSignedUp() {
        return signedUp;
    }

    public void setSignedUp(List<SignedUp> signedUp) {
        this.signedUp = signedUp;
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
        if (!(object instanceof YogaClass)) {
            return false;
        }
        YogaClass other = (YogaClass) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.YogaClass[ id=" + id + " ]";
    }
    
}
