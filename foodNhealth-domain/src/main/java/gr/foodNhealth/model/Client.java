package gr.foodNhealth.model;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
public class Client extends BaseEntity {

    @Basic
    @Column(nullable = false)
    @NotNull
    private String firstName;

    @Basic
    @Column(nullable = false)
    @NotNull
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Person.Gender gender;

    @Basic
    @Email
    @Column(unique = true)
    private String email;

    @Basic
    @Column
    private Boolean emailAuthenticated;

    @Basic
    @Size(min = 6, max=25)
    private String password;

    @OneToMany(mappedBy = "client")
    private Collection<SelectedRecipe> selectedRecipes;


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

    public Person.Gender getGender() {
        return gender;
    }

    public void setGender(Person.Gender gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEmailAuthenticated() {
        return emailAuthenticated;
    }

    public void setEmailAuthenticated(Boolean emailAuthenticated) {
        this.emailAuthenticated = emailAuthenticated;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<SelectedRecipe> getSelectedRecipes() {
        return selectedRecipes;
    }

    public void setSelectedRecipes(Collection<SelectedRecipe> selectedRecipes) {
        this.selectedRecipes = selectedRecipes;
    }

    public enum Gender {
        MALE, FEMALE;
    }
}
