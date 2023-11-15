package sasps.documentmanagement.entities;

import java.util.List;

public class DocumentFolder implements DocumentComponent {
    private String folderName;
    private List<DocumentComponent> documents;

    public void addDocument(DocumentComponent document) {
        documents.add(document);
    }
    @Override
    public String display() {
        return null;
    }
}
