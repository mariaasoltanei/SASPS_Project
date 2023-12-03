package sasps.documentmanagement.controllers.interfaces;

import org.springframework.stereotype.Service;
import sasps.documentmanagement.dtos.DocumentDTO;
import sasps.documentmanagement.entities.Person;

import java.util.UUID;

@Service
public interface DocumentService {
    DocumentDTO findByID(UUID id);
    UUID createDocument(DocumentDTO documentDTO, Person person);
    UUID deleteDocument(UUID documentID);
    UUID updateDocument(UUID id, DocumentDTO documentDTO);

    DocumentDTO proxyFindByID(UUID id, String token) throws Exception;
    UUID proxyCreateDocument(DocumentDTO documentDTO) throws Exception;
    UUID proxyDeleteDocument(UUID documentID, String token) throws Exception;
    UUID proxyUpdateDocument(UUID documentID, DocumentDTO documentDTO) throws Exception;
}
