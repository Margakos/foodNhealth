package gr.foodNhealth.model;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class Client extends BaseEntity {

    @Basic
    private String firstName;

    @Basic
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Basic
    @Size(min = 6, max=30)
    private String password;

    @Basic
    @Email
    @Column(unique = true)
    private String email;

    @Basic
    private String address;

    @Basic
    private String city;

    @Basic
    private String zipCode;


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

    public String getAddress() {
        return address;
    }

    public void setAddress(String adress) {
        this.address = adress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public enum Gender {
        MALE, FEMALE;
    }
}
