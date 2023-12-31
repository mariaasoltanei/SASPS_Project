package sasps.documentmanagement.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import sasps.documentmanagement.services.JwtUserDetailsService;
import sasps.documentmanagement.config.JwtConfig;
import sasps.documentmanagement.controllers.interfaces.DocumentService;
import sasps.documentmanagement.dtos.DocumentDTO;
import sasps.documentmanagement.entities.Person;
import sasps.documentmanagement.entities.Role;
import sasps.documentmanagement.repos.PersonRepository;
import sasps.documentmanagement.services.DocumentServiceImpl;

import java.util.UUID;

@Service
public class DocumentProxy implements DocumentService {

    private final DocumentService documentService;
    private final JwtConfig jwtConfig;
    private final JwtUserDetailsService userDetailsService;
    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentProxy.class);
    private final PersonRepository personRepository;

    @Autowired
    public DocumentProxy(DocumentServiceImpl documentService, JwtConfig jwtConfig, JwtUserDetailsService userDetailsService,
                         PersonRepository personRepository) {
        this.documentService = documentService;
        this.jwtConfig = jwtConfig;
        this.userDetailsService = userDetailsService;
        this.personRepository = personRepository;
    }
    @Override
    public DocumentDTO findByID(UUID id) {
        throw new UnsupportedOperationException("This method is not allowed in this class!");
    }

    @Override
    public UUID createDocument(DocumentDTO documentDTO, Person person) {
        throw new UnsupportedOperationException("This method is not allowed in this class!");
    }

    @Override
    public UUID deleteDocument(UUID documentID) {
        throw new UnsupportedOperationException("This method is not allowed in this class!");
    }

    @Override
    public UUID updateDocument(UUID id, DocumentDTO documentDTO) {
        throw new UnsupportedOperationException("This method is not allowed in this class!");
    }

    private boolean validateRole(UserDetails loggedInUser){
        return loggedInUser.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(Role.ROLE_USER.name()));
    }
    private boolean validateToken(String token, UserDetails loggedInUser){
        return jwtConfig.validateToken(token, loggedInUser);
    }

    private void verifyUser(String token) throws Exception {
        UserDetails loggedInUser = userDetailsService.loadUserByUsername(jwtConfig.extractUsername(token));
        if(!validateToken(token,loggedInUser)){
            LOGGER.warn("User is not authorized to create document");
            throw new Exception("INVALID_TOKEN");
        }
        if(!validateRole(loggedInUser)) {
            LOGGER.warn("User is not authorized to create document");
            throw new Exception("USER_NOT_AUTHORIZED");
        }

    }
    @Override
    public DocumentDTO proxyFindByID(UUID id, String token) throws Exception {
        verifyUser(token);
        LOGGER.info("Fetched document "+id);
        return documentService.findByID(id);
    }

    @Override
    public UUID proxyCreateDocument(DocumentDTO documentDTO) throws Exception {
        verifyUser(documentDTO.getToken());
        Person person = personRepository.findByUsername(jwtConfig.extractUsername(documentDTO.getToken()));
        UUID createdDocumentID = documentService.createDocument(documentDTO,person);
        LOGGER.info("Created document " + documentDTO.getName());
        return createdDocumentID;
    }

    @Override
    public UUID proxyDeleteDocument(UUID documentID, String token) throws Exception {
        verifyUser(token);
        UUID deletedDocumentId = documentService.deleteDocument(documentID);
        LOGGER.info("Deleted document with ID " + deletedDocumentId);
        return deletedDocumentId;
    }

    @Override
    public UUID proxyUpdateDocument(UUID id, DocumentDTO documentDTO) throws Exception {
        verifyUser(documentDTO.getToken());
        UUID updatedDocumentId = documentService.updateDocument(id, documentDTO);
        LOGGER.info("Updated document with ID " + updatedDocumentId);
        return updatedDocumentId;
    }
}
