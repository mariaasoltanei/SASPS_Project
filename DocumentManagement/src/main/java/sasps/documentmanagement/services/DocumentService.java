package sasps.documentmanagement.services;

import org.springframework.stereotype.Service;
import sasps.documentmanagement.repos.DocumentRepository;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;

    public DocumentService(DocumentRepository documentRepository){
        this.documentRepository = documentRepository;
    }


}
