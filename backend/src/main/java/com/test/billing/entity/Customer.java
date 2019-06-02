package com.test.billing.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(optional = false)
    @JoinColumn(name = "person_id", nullable = false, unique = true)
    private Person person;
    private Date firstOrder;
    private Date lastOrder;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Date getFirstOrder() {
        return firstOrder;
    }

    public void setFirstOrder(Date firstOrder) {
        this.firstOrder = firstOrder;
    }

    public Date getLastOrder() {
        return lastOrder;
    }

    public void setLastOrder(Date lastOrder) {
        this.lastOrder = lastOrder;
    }
}
