package gr.foodNhealth.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

@MappedSuperclass
public class BaseSubstancesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Size(max = 255)
    @Column(length = 255) //default, set here only to match the @Max annotation size
    private String title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
