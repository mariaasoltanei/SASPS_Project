package sasps.documentmanagement.entities;

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
@Inheritance(strategy = InheritanceType.JOINED)
public class Folder extends DocumentComponent {

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "folder_id")
    private List<Document> documents;

    public void addDocument(Document document) {
        documents.add(document);
    }

    @Override
    public String display() {
        return
                """
                        Folder: %s
                        Upload date: %s
                        Last modified date: %s
                        Person: %s
                        Documents: %s
                        """.formatted(name, uploadDate, lastModified, person, documents.stream().map(DocumentComponent::display).toList());
    }
}
