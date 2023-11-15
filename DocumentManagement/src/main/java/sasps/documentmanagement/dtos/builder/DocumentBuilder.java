package sasps.documentmanagement.dtos.builder;

import sasps.documentmanagement.dtos.DocumentDTO;
import sasps.documentmanagement.entities.Document;

import java.util.UUID;

public class DocumentBuilder {
    public static Document toDocument(DocumentDTO documentDTO) {
        return new Document(UUID.randomUUID(), documentDTO.getName(), documentDTO.getUploadDate(), documentDTO.getLastModifiedDate(), documentDTO.getPerson());
    }

    public static DocumentDTO toDocumentDTO(Document document) {
        return new DocumentDTO(document.getId(), document.getName(), document.getUploadDate(), document.getLastModifiedDate(), document.getPerson());
    }
}
