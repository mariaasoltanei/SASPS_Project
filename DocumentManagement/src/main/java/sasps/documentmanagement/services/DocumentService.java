package sasps.documentmanagement.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import sasps.documentmanagement.dtos.DocumentDTO;
import sasps.documentmanagement.dtos.builder.DocumentBuilder;
import sasps.documentmanagement.entities.Document;
import sasps.documentmanagement.repos.DocumentRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class DocumentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentService.class);
    private final DocumentRepository documentRepository;

    public DocumentService(DocumentRepository documentRepository){
        this.documentRepository = documentRepository;
    }


    public DocumentDTO findByID(UUID id) {
        Optional<Document> documentOptional = documentRepository.findById(id);
        if(documentOptional.isEmpty()){
            LOGGER.error("Document with id("+id+") doesn't exist!");
            return null;
        }
        Document document = documentOptional.get();
        return DocumentBuilder.toDocumentDTO(document);
    }

    public UUID createDocument(DocumentDTO documentDTO) {
        Optional<Document> documentOptional = documentRepository.findByName(documentDTO.getName());
        if(documentOptional.isPresent()) {
            LOGGER.error("Document with name("+documentDTO.getName()+") exists already");
            return null;
        }
        Document document = DocumentBuilder.toDocument(documentDTO);
        documentRepository.save(document);
        return document.getId();
    }

    public UUID deleteDocument(UUID id){
        Optional<Document> documentOptional = documentRepository.findById(id);
        if(documentOptional.isEmpty()){
            LOGGER.error("Document with id("+id+") doesn't exist!");
            throw new ResourceNotFoundException("Document with id("+id+") doesn't exist!");
        }
        Document document = documentOptional.get();
        documentRepository.delete(document);
        return document.getId();
    }

    public UUID updateDocument(UUID id, DocumentDTO documentDTO){
        Optional<Document> documentOptional = documentRepository.findById(id);
        if(documentOptional.isEmpty()){
            LOGGER.error("Document with id("+id+") doesn't exist!");
            throw new ResourceNotFoundException("Document with id("+id+") doesn't exist!");
        }
        Document document = documentOptional.get();
        document.setName(documentDTO.getName());
        document.setUploadDate(documentDTO.getUploadDate());
        document.setLastModifiedDate(documentDTO.getLastModifiedDate());
        document.setPerson(documentDTO.getPerson());
        Document updatedDocument = documentRepository.save(document);
        return updatedDocument.getId();
    }
}
