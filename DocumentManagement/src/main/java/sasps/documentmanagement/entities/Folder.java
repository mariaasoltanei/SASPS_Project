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
@DiscriminatorValue("folder")

public class Folder extends DocumentComponent {
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "folder_components",
            joinColumns = @JoinColumn(name = "folder_id"),
            inverseJoinColumns = @JoinColumn(name = "document_id")
    )
    private List<DocumentComponent> documents;

    public void addDocument(DocumentComponent document) {
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
