package sasps.antipatterndocumentmanagement;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "upload_date",nullable = false)
    private Date uploadDate;

    @Column(name = "last_modified",nullable = false)
    private Date lastModified;

    @ManyToOne(optional = false)
    @JoinColumn(name = "owner_id", nullable = false)
    private Person person;

    @Override
    public String toString() {
        return """
                Document: %s
                Upload date: %s
                Last modified date: %s
                Person: %s
                """.formatted(name, uploadDate, lastModified, person);
    }
}
