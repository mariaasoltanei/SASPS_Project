package sasps.documentmanagement.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    @Column(name = "extension")
    public Extension extension;

    @ManyToOne(optional = false,cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id", nullable = false)
    public Person person;
    abstract String display();
}
