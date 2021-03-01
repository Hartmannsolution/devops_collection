/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author root
 */
@Entity
@Table(name="student")
@NamedQuery(name = "Student.deleteAllRows", query = "DELETE FROM Student")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name="phone")
    private int phone;
    @Column(name="email")
    private String email;
    
    @OneToMany(mappedBy = "student", cascade= CascadeType.PERSIST)
    private List<SignedUp> signedUp;

    public Student() {
    }

    public Student(String name, int phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public Student(String name, int phone, String email, List<SignedUp> signedUp) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.signedUp = signedUp;
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
    
}
