package com.test.billing.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private Boolean isAffiliate;

    @OneToOne(cascade =  CascadeType.ALL, mappedBy = "person")
    private Employee employeeProfile;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Employee getEmployeeProfile() {
        return employeeProfile;
    }

    public void setEmployeeProfile(Employee employeeProfile) {
        this.employeeProfile = employeeProfile;
    }

    public Boolean getAffiliate() {
        return isAffiliate;
    }

    public void setAffiliate(Boolean affiliate) {
        isAffiliate = affiliate;
    }
}
