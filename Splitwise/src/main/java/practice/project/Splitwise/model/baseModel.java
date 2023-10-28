package practice.project.Splitwise.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public abstract class baseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @CreatedDate
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date createdAt;

    @LastModifiedDate
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date lastModifiedAt;

    @PrePersist
    public void prePersist() {
        Date currentDate = new Date();
        this.createdAt = currentDate;
        this.lastModifiedAt = currentDate;
    }

    @PreUpdate
    public void preUpdate() {
        this.lastModifiedAt = new Date();
    }
}
