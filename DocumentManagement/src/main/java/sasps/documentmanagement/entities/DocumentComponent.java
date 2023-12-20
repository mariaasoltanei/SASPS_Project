package sasps.documentmanagement.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "document_type")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "document_component")
public abstract class DocumentComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;

    @Column(name = "name", nullable = false)
    public String name;

    @Column(name = "upload_date", nullable = false)
    public Date uploadDate;

    @Column(name = "last_modified_date")
    public Date lastModified;

    @ManyToOne(optional = false)
    @JoinColumn(name = "person_id", nullable = false)
    public Person person;
    abstract String display();
}
