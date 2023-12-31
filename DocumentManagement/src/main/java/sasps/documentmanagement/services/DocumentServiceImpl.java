package sasps.documentmanagement.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sasps.documentmanagement.controllers.interfaces.DocumentService;
import sasps.documentmanagement.dtos.DocumentDTO;
import sasps.documentmanagement.dtos.adapter.DocumentAdapter;
import sasps.documentmanagement.entities.Document;
import sasps.documentmanagement.entities.DocumentComponent;
import sasps.documentmanagement.entities.Person;
import sasps.documentmanagement.entities.factory.DocumentComponentFactory;
import sasps.documentmanagement.entities.factory.DocumentFactory;
import sasps.documentmanagement.entities.factory.FolderFactory;
import sasps.documentmanagement.exceptions.DocumentNotFoundException;
import sasps.documentmanagement.repos.DocumentRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class DocumentServiceImpl implements DocumentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentServiceImpl.class);
    private final DocumentRepository documentRepository;

    @Autowired
    public DocumentServiceImpl(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public DocumentDTO findByID(UUID id) {
        try {
            if (id == null)
                throw new IllegalArgumentException("Id is null!");
            Document document = (Document) documentRepository.findById(id).orElseThrow(() -> new DocumentNotFoundException("Document with id(" + id + ") doesn't exist!"));
            return DocumentAdapter.toDocumentDTO(document);
        } catch (DocumentNotFoundException | IllegalArgumentException e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
    }

    public UUID createDocument(DocumentDTO documentDTO, Person person) throws DocumentNotFoundException {
        DocumentComponentFactory documentComponentFactory;
        try{
            Document searchedDocument = (Document) documentRepository.findByName(documentDTO.getName()).orElse(null);
            if (searchedDocument != null)
                throw new DocumentNotFoundException("Document with name(" + documentDTO.getName() + ") already exists!");
            else {
                if(documentDTO.getExtension() == null)
                {
                    documentComponentFactory = new FolderFactory();
                    DocumentComponent folder = documentComponentFactory.createDocumentComponent(documentDTO.getName(), documentDTO.getUploadDate(), documentDTO.getLastModifiedDate(), person, null);
                    documentRepository.save(folder);
                }
                else
                    documentComponentFactory = new DocumentFactory();
                DocumentComponent document =
                        documentComponentFactory.createDocumentComponent
                                (
                                        documentDTO.getName(),
                                        documentDTO.getUploadDate(),
                                        documentDTO.getLastModifiedDate(),
                                        person,
                                        documentDTO.getExtension()
                                );
                documentRepository.save(document);
                return document.getId();
            }
        }   catch (DocumentNotFoundException e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
    }

    public UUID deleteDocument(UUID id) throws DocumentNotFoundException {
        Optional<DocumentComponent> documentOptional = documentRepository.findById(id);
        if (documentOptional.isEmpty()) {
            LOGGER.error("Document with id(" + id + ") doesn't exist!");
            throw new DocumentNotFoundException("Document with id(" + id + ") doesn't exist!");
        }
        Document document = (Document) documentOptional.get();
        documentRepository.delete(document);
        return document.getId();
    }

    public UUID updateDocument(UUID id, DocumentDTO documentDTO) throws DocumentNotFoundException {
        Optional<DocumentComponent> documentOptional = documentRepository.findById(id);
        if (documentOptional.isEmpty()) {
            LOGGER.error("Document with id(" + id + ") doesn't exist!");
            throw new DocumentNotFoundException("Document with id(" + id + ") doesn't exist!");
        }
        Document document = (Document) documentOptional.get();
        document.setName(documentDTO.getName());
        document.setUploadDate(documentDTO.getUploadDate());
        document.setLastModified(documentDTO.getLastModifiedDate());
        //document.setPerson(documentDTO.getPerson());
        Document updatedDocument = documentRepository.save(document);
        return updatedDocument.getId();
    }

    @Override
    public DocumentDTO proxyFindByID(UUID id, String token) {
        throw new UnsupportedOperationException("This method is not allowed in this class!");
    }

    @Override
    public UUID proxyCreateDocument(DocumentDTO documentDTO) {
        throw new UnsupportedOperationException("This method is not allowed in this class!");
    }

    @Override
    public UUID proxyDeleteDocument(UUID documentID, String token) {
        throw new UnsupportedOperationException("This method is not allowed in this class!");
    }

    @Override
    public UUID proxyUpdateDocument(UUID documentID, DocumentDTO documentDTO) {
        throw new UnsupportedOperationException("This method is not allowed in this class!");
    }
}
