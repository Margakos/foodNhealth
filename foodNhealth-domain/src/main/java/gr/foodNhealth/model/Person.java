package gr.foodNhealth.model;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
public class Person extends BaseEntity{

    @Basic
    private String firstName;

    @Basic
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Basic
    @Size(min = 6, max=20)
    private String password;

    @Basic
    @Email
    @Column(unique = true)
    private String email;

    @Basic
    private String comments;

    @ManyToMany
    private Collection<Client> clients;


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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Collection<Client> getClients() {
        return clients;
    }

    public void setClients(Collection<Client> clients) {
        this.clients = clients;
    }

    public enum Gender {
        MALE, FEMALE;
    }
}
