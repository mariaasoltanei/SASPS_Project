package sasps.documentmanagement.dtos.adapter;

import sasps.documentmanagement.dtos.DocumentDTO;
import sasps.documentmanagement.entities.Document;

import java.util.UUID;

public class DocumentAdapter {
    public static Document toDocument(DocumentDTO documentDTO) {
        return new Document(UUID.randomUUID(), documentDTO.getName(), documentDTO.getUploadDate(), documentDTO.getLastModifiedDate(), null);
    }

    public static DocumentDTO toDocumentDTO(Document document) {
        return new DocumentDTO(document.getName(), document.getUploadDate(), document.getLastModifiedDate(), null, null);
    }
}
