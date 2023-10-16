package com.etiqa.bookstoremanager.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUST_SEQ_GEN")
    @SequenceGenerator(name = "CUST_SEQ_GEN", sequenceName = "customer_seq", allocationSize = 1)
    private Long id;

    private String firstName;
    private String lastName;

    @Embedded
    private Email email;

    @ElementCollection
    @Column(name = "FAMILY_MEMBER_NAME")
    private List<String> familyMembers;

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

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public List<String> getFamilyMembers() {
        return familyMembers;
    }

    public void setFamilyMembers(List<String> familyMembers) {
        this.familyMembers = familyMembers;
    }
}

@Embeddable
class Email {
    @Column(name = "OFFICE_EMAIL")
    private String office;
    @Column(name = "PERSONAL_EMAIL")
    private String personal;

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getPersonal() {
        return personal;
    }

    public void setPersonal(String personal) {
        this.personal = personal;
    }
}
