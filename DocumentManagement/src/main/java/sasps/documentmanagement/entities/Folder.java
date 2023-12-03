package sasps.documentmanagement.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Folder implements DocumentComponent {
    private String name;
    private List<DocumentComponent> documents;

    public void addDocument(DocumentComponent document) {
        documents.add(document);
    }
    @Override
    public String display() {
        return null;
    }
}
