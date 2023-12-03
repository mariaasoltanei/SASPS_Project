/*
package sasps.documentmanagement.services.decorator;

import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sasps.documentmanagement.dtos.DocumentDTO;
import sasps.documentmanagement.exceptions.DocumentNotFoundException;
import sasps.documentmanagement.services.DocumentServiceImpl;

import java.util.UUID;

@NoArgsConstructor
@Service
public class LoggingDecorator implements DocumentServiceImpl {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingDecorator.class);

    @Override
    public DocumentDTO findByID(UUID id) {
        LOGGER.info("Finding document by ID: {}", id);
        DocumentDTO documentDTO = super.findByID(id);
        if (documentDTO == null) {
            LOGGER.warn("Document with ID {} not found", id);
        }
        return documentDTO;
    }

    @Override
    public UUID createDocument(DocumentDTO documentDTO) throws DocumentNotFoundException {
        LOGGER.info("Creating document with name: {}", documentDTO.getName());
        try {
            return super.createDocument(documentDTO);
        } catch (DocumentNotFoundException e) {
            LOGGER.error("Error creating document: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public UUID deleteDocument(UUID id) throws DocumentNotFoundException {
        LOGGER.info("Deleting document with ID: {}", id);
        try {
            return super.deleteDocument(id);
        } catch (DocumentNotFoundException e) {
            LOGGER.error("Error deleting document: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public UUID updateDocument(UUID id, DocumentDTO documentDTO) throws DocumentNotFoundException {
        LOGGER.info("Updating document with ID: {}", id);
        try {
            return super.updateDocument(id, documentDTO);
        } catch (DocumentNotFoundException e) {
            LOGGER.error("Error updating document: {}", e.getMessage());
            throw e;
        }
    }
}*/
