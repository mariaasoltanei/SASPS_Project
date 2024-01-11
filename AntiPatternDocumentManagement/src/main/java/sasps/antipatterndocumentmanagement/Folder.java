package sasps.antipatterndocumentmanagement;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Folder {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private Date uploadDate;
    private Date lastModified;

    @ManyToOne(cascade = CascadeType.ALL)
    private Person person;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Document> documents;

    public void addDocument(Document document) {
        documents.add(document);
    }

    @Override
    public String toString() {
        return
                """
                        Folder: %s
                        Upload date: %s
                        Last modified date: %s
                        Person: %s
                        Documents: %s
                        """.formatted(name, uploadDate, lastModified, person, documents.stream().map(Document::toString).toList());
    }
}
