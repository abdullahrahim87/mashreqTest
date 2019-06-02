package com.test.billing.entity;

import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;

public class Affiliate implements Serializable {
    @Id
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "person_id", nullable = false)
    Person person;

    String affiliationDetail;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getAffiliationDetail() {
        return affiliationDetail;
    }

    public void setAffiliationDetail(String affiliationDetail) {
        this.affiliationDetail = affiliationDetail;
    }
}
