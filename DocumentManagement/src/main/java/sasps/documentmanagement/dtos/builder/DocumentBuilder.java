package sasps.documentmanagement.dtos.builder;

import sasps.documentmanagement.dtos.DocumentDTO;
import sasps.documentmanagement.entities.Document;

public class DocumentBuilder {
    private static Document toDocument(DocumentDTO documentDTO) {
        return new Document(documentDTO.getId(), documentDTO.getName(), documentDTO.getContent(), documentDTO.getUploadDate(), documentDTO.getLastModifiedDate());
    }

    private static DocumentDTO toDocumentDTO(Document document) {
        return new DocumentDTO(document.getId(), document.getName(), document.getContent(), document.getUploadDate(), document.getLastModifiedDate());
    }
}
