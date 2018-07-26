package gr.foodNhealth.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@MappedSuperclass
public class BaseEntityNoId {

    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Boolean isActive;

    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Boolean isDeleted;

    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Timestamp createDate;

    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Timestamp updateDate;

    @Basic
    @Size(max = 255)
    @Column(length = 255) //default, set here only to match the @Max annotation size
    private String title;

    @Basic
    @Size(max = 1000)
    @Column(length = 1000)
    private String description;

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }
}
