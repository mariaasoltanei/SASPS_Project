package sasps.documentmanagement.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Document extends DocumentComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "upload_date",nullable = false)
    private Date uploadDate;

    @Column(name = "last_modified_date")
    private Date lastModified;

    @Column(name = "extension", insertable = false, updatable = false)
    private Extension extension;

    @ManyToOne(optional = false)
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    public String getDiscriminatorValue() {
        return this.getClass().getAnnotation(DiscriminatorValue.class).value();
    }
    @Override
    public String display() {
        return """
                Document: %s
                Upload date: %s
                Last modified date: %s
                Person: %s
                Extension: %s
                """.formatted(name, uploadDate, lastModified, person, getDiscriminatorValue());
    }
}
